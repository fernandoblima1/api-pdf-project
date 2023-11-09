import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlToPdfController {
    @Autowired
    private PdfService pdfService;

    @GetMapping("/ola")
    public String ola() {
        System.out.println("Olá, mundo!");
        return  "Olá, mundo!";
    }
    
   @PostMapping("/convert")
   public ResponseEntity<InputStreamResource> convertHtmlToPdf(@RequestBody String htmlContent) {
       ByteArrayInputStream pdfStream = pdfService.convertHtmlToPdf(htmlContent);
       HttpHeaders headers = new HttpHeaders();
       headers.add("Content-Disposition", "attachment; filename=output.pdf");
       return ResponseEntity.ok()
               .headers(headers)
               .contentType(MediaType.APPLICATION_PDF)
               .body(new InputStreamResource(pdfStream));
   }
}
