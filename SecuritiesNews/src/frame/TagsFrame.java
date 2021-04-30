package frame;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tag.Tag;

public class TagsFrame extends JFrame { // giao diện chứa các tags
	
	JPanel panel;
	
	public TagsFrame() {
		initUI();
	}
	
	// khởi tạo GUI
	private void initUI() {
		panel = new JPanel();
		add(panel);
		setTitle("Select tags");
		setSize(400, 400);
		setLocationRelativeTo(null);
	}
	
	// tạo mới check box
	private JCheckBox createCheckBox(String name) {
		JCheckBox checkBox = new JCheckBox(name);
		return checkBox;
	}
	
	// kiểm tra xem tagName đã được chọn chưa
	public boolean isSelected(String tagName) {
		for (int i = 0; i < panel.getComponentCount(); i++) {
			JCheckBox checkBox = (JCheckBox) panel.getComponent(i);
			if (checkBox.isSelected()) {
				if (tagName.equalsIgnoreCase(checkBox.getText())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addTag(Tag tag) {
		panel.add(createCheckBox(tag.getTagName()));
	}
	
	public void addTagName(String tagName) {
		panel.add(createCheckBox(tagName));
	}
	
	public void removeAllTags() {
		panel.removeAll();
	}
	
}
