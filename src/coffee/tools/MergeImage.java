package coffee.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class MergeImage {

	/**
	 * 功能：取出文件夹imageFilePath中所有的图片，拼接成一张图片，保存至路径finalPath + "\\" + finalName
	 * @param List <File> fileList,String finalPath, String finalNam
	 */

	public static String plus(String imageFilePath, String finalPath, String finalName) {
		String finalPahtName = finalPath + "\\" + finalName;
		File file = new File(imageFilePath);
		File[] fileList = file.listFiles();
		try {
			int widthNew = 0;
			int heightNew = 0;
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			List<Integer> widths = new ArrayList<Integer>();
			List<Integer> heights = new ArrayList<Integer>();
			// 读图 计算宽高 read pictures and calculate the width and height of the
			// final picture
			for (int i = 0; i < fileList.length; i++) {
				BufferedImage image = ImageIO.read(fileList[i]);
				images.add(image);
				widths.add(image.getWidth());
				heights.add(image.getHeight());
				if (widthNew < image.getWidth())
					widthNew = image.getWidth();
				heightNew += image.getHeight();
			}

			// 创建新图 写像素 creat a new picture and insert into all of the px
			BufferedImage ImageNew = new BufferedImage(widthNew, heightNew, BufferedImage.TYPE_INT_RGB);
			// 设置背景 set the background color
			Graphics g = ImageNew.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, widthNew, heightNew);
			int heightTemp = 0;
			for (int i = 0; i < images.size(); i++) {
				// 绘制图片 draw the picture
				ImageNew.setRGB((widthNew - widths.get(i)) / 2, heightTemp, widths.get(i), heights.get(i),
				images.get(i).getRGB(0, 0, widths.get(i), heights.get(i),
				new int[widths.get(i) * heights.get(i)], 0, widths.get(i)),
				0, widths.get(i));
				heightTemp += heights.get(i);
			}
			// 保存新图 save the new picture
			File outFile = new File(finalPahtName);
			ImageIO.write(ImageNew, "jpg", outFile);
		} catch (Exception e) {
			e.printStackTrace();
			finalPahtName = "";
		}
		return finalPahtName;

	}

	public static void main(String[] args) throws FileNotFoundException {
		String path = "H:\\2 工具\\VM8\\0 目录整理\\0 系列\\推女郎(更新至19期)\\版权图";
		String finalPath = "H:\\2 工具\\VM8\\0 目录整理\\0 系列\\推女郎(更新至19期)\\版权图merge";
		//String path = "D:\\BeautyLeg-1-900";
		File root = new File(path);
		File[] folders = root.listFiles();
		for(File folder : folders){
			System.out.println("正在处理 " + folder.getName());
			MergeImage.plus(folder.getAbsolutePath(), finalPath, folder.getName()+ "(压缩拼接图).jpg");
		}
	}

}
