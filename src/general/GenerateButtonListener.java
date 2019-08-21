package general;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import org.newdawn.slick.SlickException;

public class GenerateButtonListener implements ActionListener {
	private JFrame frame;
	private HashMap<String, JComponent> fields;
	public GenerateButtonListener(JFrame frame, HashMap<String, JComponent> fields) {
		this.frame = frame;
		this.fields = fields;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			Global.settings.replace("RenderWidth", Integer.parseInt(((JTextField) fields.get("renderWidth")).getText()));
			Global.settings.replace("RenderHeight", Integer.parseInt(((JTextField) fields.get("renderHeight")).getText()));
			Global.settings.replace("BrightRadius", Integer.parseInt(((JTextField) fields.get("brightRadius")).getText()));
			Global.settings.replace("WorldHeight", Integer.parseInt(((JTextField) fields.get("worldHeight")).getText()));
			Global.settings.replace("InitialWorldWidth", Integer.parseInt(((JTextField) fields.get("initialWorldWidth")).getText()));
			if(((JCheckBox) fields.get("crosshair")).isSelected()) {
				Global.settings.replace("Crosshair", 1);
			} else {
				Global.settings.replace("Crosshair", 0);
			}
			if(((JCheckBox) fields.get("infiniteWorld")).isSelected()) {
				if(Integer.parseInt(((JTextField) fields.get("initialWorldWidth")).getText()) <= 35) {
					Global.settings.replace("InitialWorldWidth", 35);
				}
				Global.settings.replace("InfiniteWorld", 1);
			} else {
				Global.settings.replace("InfiniteWorld", 0);
			}
			Global.playerName = ((JTextField) (fields.get("playerName"))).getText();
			Global.playerImagePath = ((JTextField) (fields.get("playerImagePath"))).getText().replace("/", "//").replace("\\", "//");
			
			frame.dispose();
			Global.app.start();
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
