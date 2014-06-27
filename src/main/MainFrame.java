/**
 * 
 */
package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

/**
 * @author b1012213
 *
 */
public class MainFrame extends JFrame {
	JScrollPane scrollImagePane;
	JPanel imagePane;
	JLabel imageLabel;
	JButton loadImageButton;

//	JPanel ;
	
	private MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1, 2));
		
		scrollImagePane = new JScrollPane();
		imagePane = new JPanel();
		imagePane.setLayout(new BorderLayout());
		
		// ラベルのインスタンスを生成
		 imageLabel = new JLabel("NoImage");
		
		// ボタンのインスタンスを生成
		loadImageButton = new JButton("LoadImage");
		loadImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImage();
			}
		});
		
		scrollImagePane.setViewportView(imageLabel);
		imagePane.add(scrollImagePane, BorderLayout.CENTER);
		imagePane.add(loadImageButton, BorderLayout.SOUTH);
		this.getContentPane().add(imagePane);
		
		// ヒストグラムの作成
	    HistogramDataset data = new HistogramDataset();
	    data.addSeries(null, LoadImage.getHistogramData(), 1);
	    JFreeChart chart = ChartFactory.createHistogram("Histogram", 
	                                                    "Frequency",
	                                                    "Arrivals per minute",
	                                                    data,
	                                                    PlotOrientation.VERTICAL, 
	                                                    true, 
	                                                    false, 
	                                                    false);

	    ChartPanel cpanel = new ChartPanel(chart);
	    this.getContentPane().add(cpanel);
		this.setVisible(true);
	}
	
	private void loadImage() {
		JFileChooser fc = new JFileChooser();
	    if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION){
	      File file = fc.getSelectedFile();
	      ImageIcon icon = new ImageIcon(file.getPath());
	      imageLabel.setIcon(icon);
	      
	      LoadImage li = new LoadImage(file);
	    }
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}

}
