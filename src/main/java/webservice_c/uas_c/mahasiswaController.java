/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_c.uas_c;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static webservice_c.uas_c.MahasiswaPK_.nik;

/**
 *
 * @author ROG
 */

@RestController
@CrossOrigin
@ResponseBody
public class mahasiswaController {
    
     // Membuat Objek pada table Database
    Mahasiswa data = new Mahasiswa();
    MahasiswaJpaController dctrl = new MahasiswaJpaController();
    
    // Membuat request Mapping data pake list
    @RequestMapping("/getData")
    public String getDataById(){
        List<Mahasiswa> datas = new ArrayList<>();
        try {datas = dctrl.findMahasiswaEntities();}
        catch (Exception error) {}
        return datas.toString();
    }
    
    // Membuat request getData berdasarkan ID
    @RequestMapping("/getData/{id}")
    public String getDataById(@PathVariable("id") int id){
        
        data = dctrl.findMahasiswa(nik);
        
        String result = data.getNama()+ "<p/>" + data.getMahasiswaPK().toString();
        
        return result;
    }
    
    // Membuat method AJAX GET
    @RequestMapping("/GET")
    public String getdataById(){
        
        List<Mahasiswa> datas = new ArrayList<>();
        
        try{
            datas = dctrl.findMahasiswaEntities(); 
        }
        catch(Exception error){
            
        }
        
        return datas.toString();
    }
    
    // Create Method POST
    @RequestMapping(value="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String createData(HttpEntity<String> kiriman) throws Exception{
        String message = "no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Mahasiswa data = new Mahasiswa();
        data = mapper.readValue(json_receive, Mahasiswa.class);
        dctrl.create(data);
        message = data.getNama() +" Saved";
        
        return message;
    }
    
    // Create Method PUT
    @RequestMapping(value = "/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        String message = "no action";
        
        
        ObjectMapper mapper = new ObjectMapper();
        Mahasiswa data = new Mahasiswa();
        
        data= mapper.readValue(json_receive, Mahasiswa.class);
        dctrl.edit(data);
        message = data.getNama() + " Update";
        
        
        return message;
    }
    
    // Create Method Delete
    @RequestMapping(value = "/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
       String message="no action";
        try {
            String json_receive=kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();
            Mahasiswa newdata= new Mahasiswa();
            newdata=mapper.readValue(json_receive, Mahasiswa.class);
            dctrl.destroy(newdata.getMahasiswaPK());
            message=newdata.getNama()+" berhasil dihapus";
        } catch (Exception e) {
        }
        return message;
    }
}

    

