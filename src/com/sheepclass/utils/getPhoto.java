package com.sheepclass.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class getPhoto {
    /*
     *
     */
    //视频文件路径
    private static String videoPath = "/Users/chenan/sheepclassdemo/web/VIDEO";

    //视频帧图片存储路径
    private static String videoFramesPath = "/Users/chenan/sheepclassdemo/web/SCREEN-SHOT/";

    //图片完整路径
    private String photoName;

    //暂停时间
    private double curTime;
    private int curFlames;

    //文件名
    private String videoFileName;


    public getPhoto(double curTime,String videoFileName ){
        this.curTime = curTime;
        this.videoFileName=videoFileName.substring(0,videoFileName.indexOf('.'));
        System.out.println("hhhhhh   "+this.videoFileName);

    }

    public void grabberVideoFramer(){

        /*
            获取视频文件
         */
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName+".mp4");

        try {
            fFmpegFrameGrabber.start();

            /*
             * getLengthInFrames()：获取该视频的总帧数
             * getFrameRate()：获取该视频的帧长，即一秒钟计算机可以刷新多少个图像
             *
             * 上面两个相除就是视频的长度（以秒为单位）
             * */
            int ftp = fFmpegFrameGrabber.getLengthInFrames();

            curFlames = changeStoZ(fFmpegFrameGrabber);

            //Frame对象
            Frame frame = null;


            System.out.println("FTP is "+ftp);
            System.out.println("RATE is "+fFmpegFrameGrabber.getFrameRate() );
            System.out.println("时长 is" + ftp / fFmpegFrameGrabber.getFrameRate());
            System.out.println("curFlames is " + curFlames);


            /*跳转到那帧*/
            int i = 0;
            while (i  < ftp) {

                frame = fFmpegFrameGrabber.grabImage();
                if ((i > curFlames) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            String photoName = videoFramesPath + "/" + videoFileName + String.valueOf(i)+ ".jpg";
            File picFile = new File(photoName);
            ImageIO.write(thumbnailImage, "jpg", picFile);

            //stop
            fFmpegFrameGrabber.stop();
            setphotoName(photoName);

        } catch ( IOException E) {
            E.printStackTrace();
        }




    }

    public static void main(String args[]){
        getPhoto g = new getPhoto(10.383,"history_sanguo.mp4");
        g.grabberVideoFramer();
    }

    public void setphotoName(String photoName){
        this.photoName = photoName;

    }

    public String getPhotoName(){
        return photoName;

    }

    protected int changeStoZ(FFmpegFrameGrabber fFmpegFrameGrabber){
        int curZhen = new Double(curTime*fFmpegFrameGrabber.getFrameRate()).intValue();
        return curZhen;
    }


}
