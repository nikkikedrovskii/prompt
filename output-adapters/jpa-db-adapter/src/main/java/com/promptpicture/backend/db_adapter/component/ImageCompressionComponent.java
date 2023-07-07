package com.promptpicture.backend.db_adapter.component;

import com.promptpicture.backend.jpa.prompt.repository.PromptPictureEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ImageCompressionComponent {

    public String compressImage(String b64Json, String resolution){
        try {
            // Декодирование изображения из формата base64 в массив байтов
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(b64Json);

            // Чтение изображения из массива байтов
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage inputImage = ImageIO.read(bis);

            // Создание нового изображения с размером 512x512
            BufferedImage outputImage = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = outputImage.createGraphics();

            // Масштабирование исходного изображения до размера 512x512
            Image scaledImage = inputImage.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage, 0, 0, null);
            graphics.dispose();

            // Кодирование сжатого изображения обратно в формат base64
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(outputImage, "JPEG", bos);
            byte[] compressedImageBytes = bos.toByteArray();

            return javax.xml.bind.DatatypeConverter.printBase64Binary(compressedImageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b64Json;
    }
}
