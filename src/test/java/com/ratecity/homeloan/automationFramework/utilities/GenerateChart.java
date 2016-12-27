package com.ratecity.homeloan.automationFramework.utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Color; 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;



public class GenerateChart  {
	
	static final String graphPath=System.getProperty("user.dir")+File.separator+"Reports"+File.separator+"PieChart.jpeg"; 
    static DefaultPieDataset dataset = new DefaultPieDataset(); 
   

	public static void generatePieChart()  {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Pass", BaseClass.pass.size());
		dataSet.setValue("Fail", BaseClass.fail.size());
		dataSet.setValue("Skipped",BaseClass.skip.size());
		JFreeChart chart = ChartFactory.createPieChart("Test Summary", dataSet, true, true, false);
		 PiePlot ColorConfigurator = (PiePlot)chart.getPlot();
		 ColorConfigurator.setSectionPaint("Pass", new Color(0,153,0));
		 ColorConfigurator.setSectionPaint("Fail", Color.RED);
		 ColorConfigurator.setSectionPaint("Skipped", Color.YELLOW);
		int width = 400; /* Width of the image */
		int height = 250; /* Height of the image */
		 float quality=1; 
		File pieChart = new File(graphPath ); 
		try{
		ChartUtilities.saveChartAsJPEG(pieChart ,quality, chart , width , height );
		}catch(Exception e){
			System.out.println("Exceptione creating the chart");
		}
	}
	
	public static String getGraphPath(){
		return graphPath;
	}
	 

}

