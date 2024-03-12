package hiyoweb.service;

import hiyoweb.model.dao.ProductDao;
import hiyoweb.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {//class start
    @Autowired
    private ProductDao productDao;
    @Autowired
    private FileService fileService;

    // 1. 등록 서비스요청
    public boolean postProductRegister(ProductDto productDto){
        System.out.println("ProductService.postProductRegister");
        
        // 1. 첨부파일 처리
        List<String > list = new ArrayList<>();

        productDto.getUploadFiles().forEach((uploadFiles)->{
            String fileName = fileService.fileUpload(uploadFiles);
            if(fileName !=null) list.add(fileName);
        });
        productDto.setPimg(list);

        return productDao.postProductRegister(productDto);
    }

    // 2. 제품출력( 지도에 출력할 내용 )요청
    public List<ProductDto> getProductList(){
        System.out.println("ProductService.getProductList");

        return productDao.getProductList();
    }

// ====================================================
}//class end
