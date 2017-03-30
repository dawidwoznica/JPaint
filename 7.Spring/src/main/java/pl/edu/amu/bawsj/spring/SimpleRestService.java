package pl.edu.amu.bawsj.spring;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;



/**
 * Created by Bocian on 12.01.2017.
 */
@RestController
public class SimpleRestService {

    byte[] result;

    /*@RequestMapping("/getModel")
    public ResponseEntity<ModelClass> model(){
        ModelClass modelClass = new ModelClass();
        modelClass.setA("asfasd");
        modelClass.setB("asfasd");
        modelClass.setC("asfasd");
        ModelClass2 modelClass2 = new ModelClass2();
        modelClass2.setD("asdfasdf");
        modelClass2.setE("asdfasdf");
        modelClass2.setF("asdfasdf");
        modelClass.setModelClass2List(Arrays.asList(modelClass2));

        return ResponseEntity.ok(modelClass);
    }*/

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getJson(@RequestBody String input) {

        result = Base64.decodeBase64(input);

        System.out.println(result);

        return "redirect:homeGet";
    }


   @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] homeGet(){
       return result;
    }



}


