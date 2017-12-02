package org.safari.platform.modules.rept.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class ReportUtil {

	/**
	 * 生成折线图
	 * @param dataset  数据集
	 * @param title 图的标题
	 * @param xTitle 横轴标题
	 * @param yTitle 纵轴标题
	 * @return
	 */
	public static JFreeChart lineChart(CategoryDataset dataset, String title, 
			String xTitle, String yTtile) {
			
		// 构建一个chart
		JFreeChart chart = ChartFactory.createLineChart(title, // 图的标题
				xTitle,  // x轴标题
				yTtile, // y轴标题
				dataset, // 数据源
				PlotOrientation.VERTICAL,  //显示二维曲线图的方位,垂直于Y轴
				true, true, false);
		
		chart.setTextAntiAlias(true);  // 设置文本抗锯齿为false,防止乱码
		chart.setBackgroundPaint(new Color(245,252,255)); // 背景颜色
		chart.setBorderVisible(false); // 边界线条是否可见
		
		// 设置标题
		Font font = new Font("微软雅黑", Font.PLAIN, 20);
		chart.getTitle().setFont(font);
		
		// 设置图示的字体
		Font labelFont = new Font("微软雅黑", Font.PLAIN, 12);
		chart.getLegend().setItemFont(labelFont);
		
		//获取绘图区对象
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setNoDataMessage("暂无数据显示！");// 没有数据显示的时候显示这个提示
		categoryplot.setBackgroundPaint(Color.WHITE);// 区域背景颜色
		categoryplot.setRangeGridlinesVisible(true); // 网格X轴是否可见
		categoryplot.setRangeGridlinePaint(new Color(46,45,44));// X轴虚线色彩
		//categoryplot.setDomainGridlinesVisible(true);  // 网格Y轴是否可见
		//categoryplot.setDomainGridlinePaint(new Color(46,45,44));// Y轴虚线色彩
		categoryplot.setAxisOffset(new RectangleInsets(18D, 0D, 0D, 30D)); // 设置轴和面板之间的距离 上左下右
		categoryplot.setOutlineVisible(false); //设置区域边框是否可见

		// 横轴 X
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值
		// X轴上字体显示的旋转角度
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// 设置最大值距离图片右端距离
		domainAxis.setUpperMargin(0.05);

		// 纵轴 y
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setLabelFont(labelFont);// 轴标题
		numberaxis.setTickLabelFont(labelFont);// 轴数值
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);//数据轴的数据标签是否自动确定
		numberaxis.setUpperMargin(0.15); //设置最高的一个值与图片顶端的距离
		
		// 折线对象
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();
		lineandshaperenderer.setBaseShapesVisible(true); // 拐点是否可见
		lineandshaperenderer.setBaseLinesVisible(true); // 拐点间有连线可见
		lineandshaperenderer.setBaseItemLabelsVisible(true);  // 折点数据是否可见
		lineandshaperenderer.setBaseItemLabelPaint(new Color(46,45,44)); //拐点文本显示颜色
		lineandshaperenderer.setBaseItemLabelGenerator(
				new StandardCategoryItemLabelGenerator());  
		lineandshaperenderer.setSeriesPaint(0, new Color(0,255,255));  //颜色
		
		return chart;
	}
	
	/**
	 * @return
	 */
	public static JFreeChart barChart(CategoryDataset dataset, String title, 
			String xTitle, String yTitle){
		
		JFreeChart chart = ChartFactory.createBarChart(title, // 图的标题
				xTitle, // X轴的标题
				yTitle, // Y轴的标题
				dataset, PlotOrientation.VERTICAL, //显示二维曲线图的方位,垂直于Y轴
				true, true, false);
		
		chart.setTextAntiAlias(true);  // 设置文本抗锯齿为false,防止乱码
		chart.setBackgroundPaint(new Color(245,252,255)); // 背景颜色
		chart.setBorderVisible(false); // 边界线条是否可见
		
		// 设置标题
		Font font = new Font("微软雅黑", Font.PLAIN, 20);
		chart.getTitle().setFont(font);
		
		// 设置图示的字体
		Font labelFont = new Font("微软雅黑", Font.PLAIN, 12);
		chart.getLegend().setItemFont(labelFont);
		
		//获取绘图区对象
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setNoDataMessage("暂无数据显示！");// 没有数据显示的时候显示这个提示
		categoryplot.setBackgroundPaint(Color.WHITE);// 区域背景颜色
		categoryplot.setRangeGridlinesVisible(true); // 网格X轴是否可见
		categoryplot.setRangeGridlinePaint(new Color(46,45,44));// X轴虚线色彩
		categoryplot.setAxisOffset(new RectangleInsets(18D, 0D, 0D, 30D)); // 设置轴和面板之间的距离 上左下右
		categoryplot.setOutlineVisible(false); //设置区域边框是否可见
		
		// 横轴 X
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值
		// X轴上字体显示的旋转角度
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		// 设置最大值距离图片右端距离
		domainAxis.setUpperMargin(0.05);

		// 纵轴 y
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setLabelFont(labelFont);// 轴标题
		numberaxis.setTickLabelFont(labelFont);// 轴数值
		numberaxis.setUpperBound(1);  //最大值
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);//数据轴的数据标签是否自动确定
		numberaxis.setUpperMargin(0.15); //设置最高的一个值与图片顶端的距离
		numberaxis.setNumberFormatOverride(NumberFormat.getPercentInstance()); 
		
		// 2D柱状图对象
		BarRenderer barRenderer = (BarRenderer) categoryplot.getRenderer();
		// 设置柱子宽度    
		barRenderer.setMaximumBarWidth(0.05);    
		// 设置柱子高度    
		barRenderer.setMinimumBarLength(0.1);    
		// 设置柱的边框可见    
		barRenderer.setDrawBarOutline(true);    
		// 设置柱的颜色(可设定也可默认)    
		barRenderer.setSeriesPaint(0, new Color(53,128,183));    
		// 设置每个地区所包含的平行柱的之间距离    
		barRenderer.setItemMargin(0.4);    
		barRenderer.setBaseItemLabelsVisible(true);
		barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",
				java.text.NumberFormat.getPercentInstance()));
	
		return chart;
	}
	
	public static CategoryDataset createDataset(){
		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		// 第一条折线数据 折线名Series 1
		localDefaultCategoryDataset.addValue(21.0D, "用户总数量", " 1月 ");
		localDefaultCategoryDataset.addValue(30.0D, "用户总数量", " 2月 ");
		localDefaultCategoryDataset.addValue(52.0D, "用户总数量", " 3月 ");
		localDefaultCategoryDataset.addValue(45.0D, "用户总数量", " 4月 ");
		localDefaultCategoryDataset.addValue(50.0D, "用户总数量", " 5月 ");
		localDefaultCategoryDataset.addValue(60.0D, "用户总数量", " 6月 ");
		localDefaultCategoryDataset.addValue(55.0D, "用户总数量", " 7月 ");
		localDefaultCategoryDataset.addValue(80.0D, "用户总数量", " 8月 ");
		localDefaultCategoryDataset.addValue(70.0D, "用户总数量", " 9月 ");
		localDefaultCategoryDataset.addValue(78.0D, "用户总数量", " 10月 ");
		localDefaultCategoryDataset.addValue(85.0D, "用户总数量", " 11月 ");
		localDefaultCategoryDataset.addValue(590.0D, "用户总数量", " 12月 ");
		
		// 第二条折线数据 折线名Series 2
		localDefaultCategoryDataset.addValue(275.0D, "Series 2", " 1月 ");
		localDefaultCategoryDataset.addValue(121.0D, "Series 2", " 2月 ");
		localDefaultCategoryDataset.addValue(98.0D, "Series 2", " 3月 ");
		localDefaultCategoryDataset.addValue(103.0D, "Series 2", " 4月 ");
		localDefaultCategoryDataset.addValue(210.0D, "Series 2", " 5月 ");
		// 第三天折线数据 折线名Series 3
		localDefaultCategoryDataset.addValue(198.0D, "Series 3", " 1月 ");
		localDefaultCategoryDataset.addValue(165.0D, "Series 3", " 2月 ");
		localDefaultCategoryDataset.addValue(55.0D, "Series 3", " 3月 ");
		localDefaultCategoryDataset.addValue(34.0D, "Series 3", " 4月 ");
		localDefaultCategoryDataset.addValue(77.0D, "Series 3", " 5月 ");
		
		return localDefaultCategoryDataset;
	}
	
	public static void main(String[] args) {
		/*DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		// 第一条折线数据 折线名Series 1
		localDefaultCategoryDataset.addValue(21.0D, "用户总数量", " 1月 ");
		localDefaultCategoryDataset.addValue(30.0D, "用户总数量", " 2月 ");
		localDefaultCategoryDataset.addValue(52.0D, "用户总数量", " 3月 ");
		localDefaultCategoryDataset.addValue(45.0D, "用户总数量", " 4月 ");
		localDefaultCategoryDataset.addValue(50.0D, "用户总数量", " 5月 ");
		localDefaultCategoryDataset.addValue(60.0D, "用户总数量", " 6月 ");
		localDefaultCategoryDataset.addValue(555.0D, "用户总数量", " 7月 ");
		localDefaultCategoryDataset.addValue(80.0D, "用户总数量", " 8月 ");
		localDefaultCategoryDataset.addValue(70.0D, "用户总数量", " 9月 ");
		localDefaultCategoryDataset.addValue(78.0D, "用户总数量", " 10月 ");
		localDefaultCategoryDataset.addValue(85.0D, "用户总数量", " 11月 ");
		localDefaultCategoryDataset.addValue(90.0D, "用户总数量", " 12月 ");
		
		// 第二条折线数据 折线名Series 2
		localDefaultCategoryDataset.addValue(275.0D, "Series 2", " 1月 ");
		localDefaultCategoryDataset.addValue(121.0D, "Series 2", " 2月 ");
		localDefaultCategoryDataset.addValue(98.0D, "Series 2", " 3月 ");
		localDefaultCategoryDataset.addValue(103.0D, "Series 2", " 4月 ");
		localDefaultCategoryDataset.addValue(210.0D, "Series 2", " 5月 ");
		// 第三天折线数据 折线名Series 3
		localDefaultCategoryDataset.addValue(198.0D, "Series 3", " 1月 ");
		localDefaultCategoryDataset.addValue(165.0D, "Series 3", " 2月 ");
		localDefaultCategoryDataset.addValue(55.0D, "Series 3", " 3月 ");
		localDefaultCategoryDataset.addValue(34.0D, "Series 3", " 4月 ");
		localDefaultCategoryDataset.addValue(77.0D, "Series 3", " 5月 ");
		
		JFreeChart freeChart = lineChart(localDefaultCategoryDataset, "总注册用户", null, null);
		
		try {
			String filename = ServletUtilities.saveChartAsPNG(freeChart, 600, 400, null);
			System.out.println(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		
		localDefaultCategoryDataset.addValue(0.1D, "占比", "中锋");
		localDefaultCategoryDataset.addValue(0.2D, "占比", "大前锋");
		localDefaultCategoryDataset.addValue(0.3D, "占比", "小前锋");
		localDefaultCategoryDataset.addValue(0.15D, "占比", "组织后卫");
		localDefaultCategoryDataset.addValue(0.25D, "占比", "得分后卫");
		
		JFreeChart freeChart = barChart(localDefaultCategoryDataset, "占比", null, null);
		
		try {
			String filename = ServletUtilities.saveChartAsPNG(freeChart, 600, 400, null);
			System.out.println(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
