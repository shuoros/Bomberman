package Code;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is for working with photos.
 * 
 * @author Soroush
 *
 */
public class PicBrowse {

	/**
	 * This method changes the size of a photo.
	 * 
	 * @param picPath
	 *            The address of a picture on computer.
	 * @param BLOBpic
	 *            Picture.
	 * @param width
	 *            Width of picture.
	 * @param height
	 *            Height of picture.
	 * @return Resized Picture.
	 */
	public ImageIcon resizePic(String picPath, byte[] BLOBpic, int width, int height) {
		ImageIcon myImg;
		ImageIcon myPicture = null;
		if (picPath != null) {
			myImg = new ImageIcon(picPath);
			Image img = myImg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			myPicture = new ImageIcon(img);
		} else {
			myImg = new ImageIcon(picPath);
		}
		return myPicture;

	}

	/**
	 * This is a method for browse a photo from computer.
	 * 
	 * @param lbl
	 *            Location of viewing picture in the graphical interface of program.
	 * @return The address of picture on computer.
	 */
	public String browseImage(JLabel lbl) {
		String path = "";
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter fF = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
		fc.addChoosableFileFilter(fF);
		int fileState = fc.showSaveDialog(null);
		if (fileState == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			path = selectedFile.getAbsolutePath();
			lbl.setIcon(resizePic(path, null, lbl.getWidth(), lbl.getHeight()));
		} else if (fileState == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "No Image Selected");
		}
		return path;
	}
}
