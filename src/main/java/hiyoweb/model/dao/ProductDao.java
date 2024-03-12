package hiyoweb.model.dao;

import hiyoweb.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends Dao {// class start

    // 1. 등록 서비스요청
    public boolean postProductRegister(ProductDto productDto){
        System.out.println("ProductDao.postProductRegister");
        System.out.println("productDto = " + productDto);
        try {

            // 1. 제품 등록
            String sql ="insert into product (pname,pprice,pcontent,plet,plng,mno)values (?,?,?,?,?,?);";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS );
            ps.setString(1,productDto.getPname());
            ps.setInt(2,productDto.getPprice());
            ps.setString(3,productDto.getPcontent());
            ps.setString(4,productDto.getPlet());
            ps.setString(5,productDto.getPlng());
            ps.setInt(6,productDto.getMno());
            int count = ps.executeUpdate();
            if(count==1){
                // 2. 이미지등록
                rs = ps.getGeneratedKeys(); // PK번호 호출 RETURN_GENERATED_KEYS
                if(rs.next()){
                    // 등록할 이미지 개수만큼 SQL 실행
                    productDto.getPimg().forEach((pimg)->{
                        try {
                            String  subSql = "insert into productimg( pimg , pno)values(?,?)";
                            ps = conn.prepareStatement(subSql);
                            ps.setString(1,pimg);
                            ps.setInt(2,rs.getInt(1));
                            ps.executeUpdate();
                        }catch (Exception e){System.out.println("e = " + e);}

                    });
                }
            }return true;


        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 2. 제품출력( 지도에 출력할 내용 )요청
    public List<ProductDto> getProductList(){
        System.out.println("ProductDao.getProductList");
        List<ProductDto> list = new ArrayList<>();
        try {
            String sql = "select * from product;";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(ProductDto.builder()
                                .pno(rs.getInt("pno"))
                                .pname(rs.getString("pname"))
                                .pprice(rs.getInt("pprice"))
                                .pstate(rs.getInt("pstate"))
                                .plet(rs.getString("plet"))
                                .plng(rs.getString("plng"))
                                .build());
            }
        }catch (Exception e){ System.out.println("e = " + e);}

        return list;
    }

}// class end
