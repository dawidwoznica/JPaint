package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import view.JPaintView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by roxwo on 17.02.2017.
 */
public class HttpController {


    public static void postChanges(JPaintView view){
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 1000); //Timeout Limit
        HttpResponse response;

        try {
            HttpPost post = new HttpPost("http://localhost:8080/" +
                    "");


            Image snapshot = view.canvas.snapshot(null, null);
            BufferedImage bImage = SwingFXUtils.fromFXImage(snapshot, null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res  = s.toByteArray();
            s.close();

            String base64String = Base64.encodeBase64String(res);


            StringEntity se = new StringEntity(base64String);
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            post.setEntity(se);
            response = client.execute(post);

            if (response != null) {
                InputStream in = response.getEntity().getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
