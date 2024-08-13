package org.firstinspires.ftc.teamcode;


// Importing camera libraries
import com.example.camerasensor.CameraSensor;
import com.example.camerasensor.ImageData;

public class CameraSensorApp {

    public static void main(String[] args) {
        // Creating new 'CameraSensor' type object called 'camera'
        CameraSensor camera = new CameraSensor();

        // Run code and print error message if error encountered
        try {
            // Open the camera sensor
            camera.open();

            // Set some configurations
            camera.setResolution(1920, 1080);
            camera.setFrameRate(30);

            // Take an image
            ImageData image = camera.captureImage();

            // Output the image data to desired folder
            outputImageData(image);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the camera sensor
            camera.close();
        }
    }
    //a;sd
    private static void outputImageData(ImageData image) {
        // save image as file in folder
        try {
            String filename = "captured_image.jpg";
            image.saveToFile(filename);
            System.out.println("Image saved to " + filename);
        } catch (Exception e) {
            System.err.println("Failed to save image: " + e.getMessage());
        }
    }
}