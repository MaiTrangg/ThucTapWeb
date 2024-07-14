package Controller;


import java.io.*;

import DAO.CategoryDAO;
import DAO.InventoryDAO;
import DAO.InventoryTransactionDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Inventory;
import Model.Product;
import jdk.nashorn.internal.objects.NativeString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
@WebServlet("/updateExcelServlet")
@MultipartConfig // Chú thích để cấu hình multipart
//@WebServlet(name = "updateExcelServlet ", value = "/updateExcelServlet ")
public class updateExcelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String IMAGE_UPLOAD_DIR = "img"; // Folder to store images

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Part filePart = request.getPart("file"); // Lấy file tải lên từ form
        System.out.println("vào updateExcel: "+filePart);
        if (filePart != null && (filePart.getSubmittedFileName().endsWith(".xls") || filePart.getSubmittedFileName().endsWith(".xlsx"))) {
            System.out.println("File uploaded is in Excel format.");
            try (InputStream fileContent = filePart.getInputStream()) {
                System.out.println("fileContent: "+fileContent);
                // Đọc và xử lý nội dung từ file Excel
                processExcelFile(fileContent,request);

                response.getWriter().println("Excel file has been processed successfully!");
                response.sendRedirect("managerProServerlet");
            } catch (InvalidFormatException e) {
                response.getWriter().println("Invalid file format: " + e.getMessage());
            } catch (Exception e) {
                response.getWriter().println("Error processing file: " + e.getMessage());
            }
        } else {
            response.getWriter().println("No file uploaded.");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }

    private void processExcelFile(InputStream inp, HttpServletRequest request) throws IOException, InvalidFormatException, SQLException {
        System.out.println("uuuuuuuuuuuuuuu");
        Workbook workbook = WorkbookFactory.create(inp);
        System.out.println("workbook: "+workbook);
        Sheet sheet = workbook.getSheetAt(0);

        Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());
        System.out.println("iiiiiiiiiiiii");
            Iterator<Row> iterator = sheet.iterator();
        System.out.println("next: "+iterator.hasNext());
            while (iterator.hasNext()) {
                System.out.println("huhu");
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue; // Bỏ qua dòng tiêu đề
                }

                int id =  Integer.parseInt(currentRow.getCell(1).getStringCellValue());
                System.out.println("id: "+id);



                Cell imageCell = currentRow.getCell(2);
                String image = "";
                String imagePath="";
                if (imageCell.getHyperlink() != null) {
                    image = imageCell.getHyperlink().getAddress();
                    imagePath=saveImageIfNotExist(image,request);

                } else if (imageCell.getCellType() == CellType.STRING) {
                    imagePath = imageCell.getStringCellValue();
                } else {
                    System.out.println("Image cell is not a string or does not contain a hyperlink");
                }


                System.out.println("Image: " + imagePath);
                String name = currentRow.getCell(3).getStringCellValue();
                System.out.println("name: "+name);
                String description = currentRow.getCell(4).getStringCellValue();
                System.out.println("description: "+description);
                double price = Double.parseDouble(currentRow.getCell(5).getStringCellValue());
                System.out.println("price: "+price);
                String category = currentRow.getCell(6).getStringCellValue();
                System.out.println("category: "+category);
                int quantity = (int) currentRow.getCell(7).getNumericCellValue();
                System.out.println("quantity: "+quantity);

                // Kiểm tra và lưu ảnh
//                String imagePath = saveImageIfNotExist(image, request);
//                System.out.println("imagePath: "+imagePath);

                //kiem tra xem category co thay doi khong
                int category_id = CategoryDAO.getIDCategory(category);

                //khởi tạo inventorypro
                Category c = new Category(category,category_id);
                Product p = new Product(id,imagePath,name,description,price,price,c);
                Inventory inventory =null;
                int newQuantity = 0;

                //check exist id product
                boolean check = ProductDAO.checkExistIDPro(id);
                if(check){
                    //thực hiên cộng thêm số lượng sản phẩm nhập với số lượng còn lại trong kho
                    newQuantity = quantity+ InventoryDAO.getInventoryPro(id).getQuantity();
                    inventory = new Inventory(0,p,newQuantity,lastUpdated);
                    InventoryDAO.updateInventory(inventory);
                }else{
                    int idpro = ProductDAO.insertProduct(p);
                    p.setProductId(idpro);
                    inventory = new Inventory(0,p,quantity,lastUpdated);
                    InventoryDAO.insertInventory(inventory);

                }
                // ghi lai giao dich nhap kho
                InventoryTransactionDAO.insertInventoryTransaction(p.getProductId(),"Nhập",quantity,lastUpdated,"Thêm sản phẩm vào kho: có mã sp: "+p.getProductId()+", tên: "+p.getName());


            }
    }


    private String saveImageIfNotExist(String imageUrl, HttpServletRequest request) throws IOException {
        if (imageUrl == null || imageUrl.isEmpty()) {
            System.err.println("Image URL is null or empty");
            return null; // Hoặc xử lý theo cách khác
        }

        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        String filePath = request.getServletContext().getRealPath("/img/") + fileName;

        File file = new File(filePath);
        if (!file.exists()) {
            try (InputStream in = new URL(imageUrl).openStream()) {
                Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image saved: " + filePath);
            } catch (IOException e) {
                System.err.println("Error saving image: " + e.getMessage());
                throw e; // Ném lại lỗi để xử lý bên ngoài nếu cần
            }
        } else {
            System.out.println("Image already exists: " + filePath);
        }

        return "img/" + fileName; // Trả về đường dẫn tương đối
    }




}