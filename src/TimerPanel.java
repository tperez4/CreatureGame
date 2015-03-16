import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Timer Panel displays the minutes and seconds of game duration
 * @author tperez4
 */
public class TimerPanel extends JPanel{

	/**
	 *
	 */
	private static final long 		serialVersionUID 		= 198661533365379910L;
	private static JLabel 			timerLabel;
	private static Timer 			timer;
	private static int 				count;
	private static ActionListener 	timerListener;
	private static int				minuteHolder			= 0;
	private static final int 		TIMER_LENGTH 			= 1000;
	private static final int		MINUTE_LENGTH			= 60;
	
	public TimerPanel() {
		super();
		timerLabel = new JLabel("00:00");
		timerLabel.setFont(new Font("Arial", 1, 15));
		this.add(timerLabel);
		
		count = 0;
		timerListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				count += 1;
				if (count == MINUTE_LENGTH) {
					minuteHolder++;
					count = 0;
				}
				
				if (count < 10 && minuteHolder == 0) {
					timerLabel.setText("00:0" + count);
				}
				
				else if (count >= 10 && minuteHolder == 0) {
					timerLabel.setText("00:" + count);
				}
				
				else if (count >= 10 && minuteHolder > 0) {
					if (minuteHolder < 10) {
						timerLabel.setText("0" + minuteHolder + ":" + count);
					}
					else if (minuteHolder > 10) {
						timerLabel.setText(minuteHolder + ":" + count);
					}
				}
				else if (count < 10 && minuteHolder > 0) {
					if (minuteHolder < 10) {
						timerLabel.setText("0" + minuteHolder + ":0" + count);
					}
					else if (minuteHolder > 10) {
						timerLabel.setText(minuteHolder + ":0" + count);
					}
				}
				
				//if (GamePanel.getCreatureList().size() == 0) {
					//timer.stop();
				//}
			}
			
		};
			timer = new Timer(TIMER_LENGTH, timerListener);
		}
	
	/**
	 * Returns the timer
	 */
	public static Timer getTimer() {
		return timer;
	}
}
