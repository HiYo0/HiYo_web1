package hiyoweb.model.dao;

import hiyoweb.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
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
            String sql = "select * from product p inner join member m on p.mno = m.no";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                    // 제품
                ProductDto productDto = ProductDto.builder()
                        .pno(rs.getInt("pno"))
                        .pname(rs.getString("pname"))
                        .pprice(rs.getInt("pprice"))
                        .pstate(rs.getInt("pstate"))
                        .plet(rs.getString("plet"))
                        .plng(rs.getString("plng"))
                        .pdate(rs.getString("pdate"))
                        .mid(rs.getString("id"))
                        .pcontent(rs.getString("pcontent"))
                        .build();
                
                List<String > imgList = new ArrayList<>(); // 제품 이미지
                    // 제품이미지
                String subsql = "select * from productimg where pno = "+productDto.getPno();
                ps = conn.prepareStatement(subsql);
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()){
                    imgList.add(rs2.getString("pimg"));
                }
                productDto.setPimg(imgList); // 제품DTO 에 제품이미지 등록

                list.add(productDto);   // 제품 목록에 제품등록
            }
        }catch (Exception e){ System.out.println("e = " + e);}

        return list;
    }

    // 3. 해당 제품의 찜하기 등록
        // 언제실행 : 로그인한 유저가 찜하기 버튼 클릭했을때
        // 매개변수 : pno
        // 리턴    : boolean ( 등록 성공/실패 )
    public boolean getPlikeWrite(int pno ,int mno){
        System.out.println("ProductDao.getPlikeWrite");
        try {
            String sql = "insert into plike values(? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,mno);
            ps.setInt(2,pno);
            int count = ps.executeUpdate();
            if(count ==1){return true;}

        }catch (Exception e){System.out.println("e = " + e);}

        return false;
    }

    // 4. 해당 제품의 찜하기 상태 출력
        // 언제실행 : 로그인한 유저가 찜하기 버튼 출력시
        // 매개변수 : pno
        // 리턴    : boolean ( 있다 / 없다 )
    public boolean getPlikeView(int pno ,int mno){
        System.out.println("ProductDao.getPlikeView");
        try {
            String sql = "select * from plike where pno = ? and mno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pno);
            ps.setInt(2,mno);
            rs = ps.executeQuery();
            if(rs.next()){return true;}

        }catch (Exception e){System.out.println("e = " + e);}

        return false;
    }

    // 5. 해당 제품의 찜하기 취소 / 삭제
        // 언제실행 : 로그인한 유저가 찜하기 버튼 클릭했을때
        // 매개변수 : pno
        // 리턴    : boolean  ( 취소 성공/실패 )
    public boolean getPlikeDelete(int pno ,int mno){
        System.out.println("ProductDao.getPlikeDelete");
        try {
            String sql = "delete from plike where pno = ? and mno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pno);
            ps.setInt(2,mno);
            int count = ps.executeUpdate();
            if(count==1){return true;}

        }catch (Exception e){System.out.println("e = " + e);}

        return false;
    }

}// class end
