package me.nahkd.totallynotspigot1_17;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Jebaited {

    public static void main(String[] args) {
		System.out.println("Loading libraries, please wait...");

        try {
			Thread.sleep(5000);

            byte[] buffer;
            
            try (InputStream audio = Jebaited.class.getClassLoader().getResourceAsStream("jebaited.wav")) {
                buffer = new byte[audio.available()];
                int off = 0;
                while (off < buffer.length) off += audio.read(buffer, off, buffer.length - off);
            }
            
            ByteArrayInputStream bufferStream = new ByteArrayInputStream(buffer);
            AudioFileFormat format = AudioSystem.getAudioFileFormat(bufferStream);
            
            bufferStream.reset();
            AudioInputStream input = AudioSystem.getAudioInputStream(bufferStream);
            
            byte[] frame = new byte[format.getFormat().getFrameSize()];
            
            SourceDataLine speaker = (SourceDataLine) AudioSystem.getSourceDataLine(format.getFormat());
            speaker.open();
            speaker.start();
            
            while (input.available() > 0) {
                input.readNBytes(frame, 0, frame.length);
                speaker.write(frame, 0, frame.length);
            }
            
            speaker.stop();
            speaker.close();
            
        } catch (Exception e) {
            System.out.println("Never gonna give you up");
            System.out.println("Never gonna let you down");
        }
    }

}
