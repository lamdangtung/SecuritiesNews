package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import inputreader.SecuritiesInputReader;
import securitiesnews.DailySecuritiesNews;
import sentenceformat.AuctionPriceRecordFormat;
import sentenceformat.AuctionStockRecordFormat;
import sentenceformat.DailyTradingLimitFormat;
import sentenceformat.DecreasingAuctionPriceTimesFormat;
import sentenceformat.DecreasingAuctionStockTimesFormat;
import sentenceformat.DecreasingFinalAuctionFormat;
import sentenceformat.DecreasingLiquidityFormat;
import sentenceformat.DecreasingPointParallelFormat;
import sentenceformat.DecreasingPointRecordFormat;
import sentenceformat.IncreasingAuctionPriceTimesFormat;
import sentenceformat.IncreasingFinalAuctionFormat;
import sentenceformat.IncreasingLiquidityFormat;
import sentenceformat.IncreasingPointFormat;
import sentenceformat.IncreasingPointRecordFormat;
import sentenceformat.IncreasingPointTimesFormat;
import sentenceformat.PointParallelFormat;
import sentenceformat.ResistantRangeFormat;
import sentenceformat.SecuritiesSentenceFormat;
import sentenceformat.SupportRangeFormat;
import tag.Tag;
import topic.SecuritiesTopic;

public class SecuritiesNewsFrame extends JFrame {	

	// các mẫu câu chứng khoán
	private final SecuritiesSentenceFormat[] SENTENCE_FORMATS = {
		new IncreasingPointFormat(),
		new IncreasingPointTimesFormat(),
		new DailyTradingLimitFormat(),
		new DecreasingLiquidityFormat(),
		new ResistantRangeFormat(),
		new SupportRangeFormat(),
		new IncreasingPointRecordFormat(),
		new DecreasingPointRecordFormat(),
		new DecreasingAuctionPriceTimesFormat(),
		new IncreasingAuctionPriceTimesFormat(),
		new AuctionPriceRecordFormat(),
		new IncreasingLiquidityFormat(),
		new DecreasingAuctionStockTimesFormat(),
		new AuctionStockRecordFormat(),
		new DecreasingFinalAuctionFormat(),
		new IncreasingFinalAuctionFormat(),
		new PointParallelFormat(),
		new DecreasingPointParallelFormat(),
	};
	
	// các tên mã chứng khoán
	private final String[] STOCK_NAMES = {
		"VN-INDEX", "VN30-INDEX", "VN100-INDEX",
	};
	
	// các tên file chứa dữ liệu đầu vào
	private final String[] FILE_INPUT_NAMES = {
		"input/VN-INDEX.csv", "input/VN30-INDEX.csv", "input/VN100-INDEX.csv",
	};
	
	// các chủ đề chứng khoán
	private final String[] TOPIC_NAMES = {
		"biến động điểm", "thanh khoản", "khớp lệnh", "dự đoán",
	};
	
	private static final String img = "Image/space-grey-ipad-air-with-graph-on-brown-wooden-table-187041 (1).jpg";
	
	private JLabel labelDayStart;
	private JLabel labelDayEnd;
	private JLabel labelStockName;
	private JLabel labelTopicName;
	private JLabel labelTitle;
	
	
	private JComboBox textDayStart;
	private JComboBox textDayEnd;
	private JComboBox textStockName;
	private JComboBox textTopicName;
	
	private JTextArea textNews;
	
	private TagsFrame tagsFrame = new TagsFrame();
	
	private List <SecuritiesTopic> topics = new ArrayList <SecuritiesTopic> (); // danh sách các chủ đề chứng khoán
	
	private List <DailySecuritiesNews> newsList = new ArrayList <DailySecuritiesNews> (); // danh sách các thông tin chứng khoán ngày
	
	public SecuritiesNewsFrame() {
		initTopics();
		initNews();
		initUI();
	}
	
	// khởi tạo các topic
	private void initTopics() {
		for (String topicName: TOPIC_NAMES) {
			topics.add(new SecuritiesTopic(topicName));
		}
		
		for (SecuritiesSentenceFormat format: SENTENCE_FORMATS) {
			for (SecuritiesTopic topic: topics) {
				if (topic.getName().equalsIgnoreCase(format.getTopicName())) {
					List <Tag> tags = format.getTags();
					for (Tag tag: tags) {
						if (!topic.containTag(tag)) {
							topic.addTag(tag);
						}
					}
				}
			}
		}
	}
	
	private void initNews() {
		SecuritiesInputReader reader = new SecuritiesInputReader();
		
		for (int i = 0; i < STOCK_NAMES.length; i++) {
			reader.readInput(STOCK_NAMES[i], FILE_INPUT_NAMES[i], newsList);
		}
		
	}
	
	//khởi tạo GUI
	private void initUI() {
		add(createMainPanel());
		add(loadBackGround());
		
		setTitle("Thông tin chứng khoán");
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	//set BackGround
	private Component loadBackGround() {
		ImageIcon backGround = new ImageIcon(img);
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backGround != null) {
					g.drawImage(backGround.getImage(), 0, 0, this);
				}
			}
		};
		return panel;
	}
	
	private void addLabels() {
		labelDayStart = new JLabel("Day start:");
		labelDayStart.setBounds(25, 45, 155, 25);
		labelDayStart.setForeground(Color.red);
		labelDayStart.setFont(new Font("", Font.BOLD, 20));
		add(labelDayStart);
		
		labelDayEnd = new JLabel("Day end:");
		labelDayEnd.setBounds(275, 45, 155, 25);
		labelDayEnd.setForeground(Color.red);
		labelDayEnd.setFont(new Font("", Font.BOLD, 20));
		add(labelDayEnd);
		
		labelStockName = new JLabel("Stock:");
		labelStockName.setBounds(125, 195, 160, 25);
		labelStockName.setForeground(Color.red);
		labelStockName.setFont(new Font("", Font.BOLD, 20));
		add(labelStockName);
		
		labelTopicName = new JLabel("Topic:");
		labelTopicName.setBounds(125, 295, 160, 25);
		labelTopicName.setForeground(Color.red);
		labelTopicName.setFont(new Font("", Font.BOLD, 20));
		add(labelTopicName);
		
		labelTitle = new JLabel("BREAKING NEWS!!!");
		labelTitle.setBounds(620, 40, 500, 30);
		labelTitle.setForeground(Color.red);
		labelTitle.setFont(new Font("", Font.BOLD, 45));	
		add(labelTitle);
	}
	
	//tạo panel chính
	private JPanel createMainPanel() {
		JPanel panel = new JPanel();
		
		addLabels();
	
		DefaultComboBoxModel dayStartModel = new DefaultComboBoxModel();
		for (DailySecuritiesNews dailyNews: newsList) {
			dayStartModel.addElement(dailyNews.getDate());
		}
		
		textDayStart = new JComboBox();
		textDayStart.setBounds(125, 45, 140, 30);
		textDayStart.setModel(dayStartModel);
		
		add(textDayStart);
		
		DefaultComboBoxModel dayEndModel = new DefaultComboBoxModel();
		for (DailySecuritiesNews dailyNews: newsList) {
			dayEndModel.addElement(dailyNews.getDate());
		}
		
		textDayEnd = new JComboBox();
		textDayEnd.setBounds(360, 45, 140, 30);
		textDayEnd.setModel(dayEndModel);
		
		add(textDayEnd);
		
		DefaultComboBoxModel stockModel = new DefaultComboBoxModel();
		for (String stockName: STOCK_NAMES) {
			stockModel.addElement(stockName);
		}
		
		textStockName = new JComboBox();
		textStockName.setBounds(215, 195, 140, 30);
		textStockName.setModel(stockModel);
		
		add(textStockName);
		
		DefaultComboBoxModel topicModel = new DefaultComboBoxModel();
		for (String topicName: TOPIC_NAMES) {
			topicModel.addElement(topicName);
		}

		textTopicName = new JComboBox();
		textTopicName.setBounds(215, 295, 155, 30);
		textTopicName.setModel(topicModel);
		
		add(textTopicName);
		
		textNews = new JTextArea();
		textNews.setEditable(false);
		textNews.setLineWrap(true);
		textNews.setWrapStyleWord(true);
		textNews.setFont(new Font("Serif", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane(textNews);
		scrollPane.setBounds(505, 105, 650, 525);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		add(scrollPane);
		
		JButton getTagsButton = new JButton("Get tags");
		getTagsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tagsFrame.removeAllTags();
				
				String currentTopicName = (String) textTopicName.getSelectedItem();
				SecuritiesTopic currentTopic = getCurrentTopic(currentTopicName);
				
				List <Tag> currentTags = currentTopic.getTags();
				for (Tag tag: currentTags) {
					tagsFrame.addTag(tag);
				}
					
				tagsFrame.setVisible(true);
			}
			
		});
		
		getTagsButton.setBounds(185, 405, 155, 45);
		getTagsButton.setForeground(Color.black);
		getTagsButton.setBackground(new Color(0, 135, 200));
		UIManager.put(getTagsButton, Color.black);
		
		add(getTagsButton);
		
		JButton getSentenceButton = new JButton("Get sentence");
		getSentenceButton.addActionListener(new ClickAction());
		getSentenceButton.setBounds(120, 515, 280, 55);
		
		add(getSentenceButton);
		
		return panel;
	}
	
	private SecuritiesTopic getCurrentTopic(String currentTopicName) {
		for (SecuritiesTopic topic: topics) {
			if (topic.getName().equalsIgnoreCase(currentTopicName)) {
				return topic;
			}
		}
		return null;
	}
	
	private class ClickAction extends AbstractAction {
   
        @Override
        public void actionPerformed(ActionEvent e) {
        	String currentDayStart = (String) textDayStart.getSelectedItem();
        	String currentDayEnd = (String) textDayEnd.getSelectedItem();
        	String currentStockName = (String) textStockName.getSelectedItem();
        	String currentTopicName = (String) textTopicName.getSelectedItem();
        	
        	// l�?c thông tin theo tên mã chứng khoán và ngày
        	List <DailySecuritiesNews> currentNewsList = new ArrayList <DailySecuritiesNews> ();
        	
        	String spliter[] = currentDayStart.split("/");
        	Date dayStart = new Date(Integer.parseInt(spliter[2]), Integer.parseInt(spliter[1]), Integer.parseInt(spliter[0]));
        	
        	spliter = currentDayEnd.split("/");
        	Date dayEnd = new Date(Integer.parseInt(spliter[2]), Integer.parseInt(spliter[1]), Integer.parseInt(spliter[0]));
        	
        	for (DailySecuritiesNews dailyNews: newsList) {
        		if (!dailyNews.getName().equalsIgnoreCase(currentStockName)) continue;
        		
        		if (dailyNews.compareDateWith(dayStart) >= 0 && dailyNews.compareDateWith(dayEnd) <= 0) {
        			currentNewsList.add(dailyNews);
        		}
        	}
        	
        	if (currentNewsList.isEmpty()) return;
        	
        	SecuritiesTopic currentTopic = getCurrentTopic(currentTopicName);
        	
        	//tìm các mẫu câu khớp với các tags mà user đã chọn
        	for (SecuritiesSentenceFormat format: SENTENCE_FORMATS) {
        		if (currentTopic.getName().equalsIgnoreCase(format.getTopicName())) {
        			List <Tag> tags = format.getTags();
        			
        			boolean check = false;
        			for (Tag tag: tags) {
        				if (tagsFrame.isSelected(tag.getTagName())) {
        					check = true;
        					break;
        				}
        			}
        			
        			if (check) {
        				format.setNewsList(currentNewsList);
        				currentTopic.addFormat(format);
        			}
        		}
        	}
        	
        	// cập nhật thông tin cho textNews
        	String news = "";
        	for (SecuritiesTopic topic: topics) {
        		String paragraph = topic.getAllContent();
        		
        		if (!paragraph.isEmpty()) news += "     ";
        		
        		news += paragraph;
        		
        		if (!paragraph.isEmpty()) {
        			news += "\n";
        		}
        	}
        	
        	textNews.setText(news);
        }
    }      
	
}
