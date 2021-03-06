import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Testing
 */
public class MFrondEnd extends javax.swing.JFrame {

    private final ArrayBlockingQueue<TiledMessage> queueRev;
    private final ArrayBlockingQueue<TiledMessage> queueFwd;
    private byte SerialNo;
    private InetAddress IpAddress;
    Timer t;
    private Thread tilethread;

    private void change_gui() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jPanel1.setBackground(Color.BLACK);
            }
        });
    }

    /**
     * Creates new form MFrondEnd
     */
    public MFrondEnd() {
        queueRev=new ArrayBlockingQueue<TiledMessage>(100);
        queueFwd=new ArrayBlockingQueue<TiledMessage>(100);
       
        initComponents();
        ipstatus=0;
        t = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//THIS SHOULD JUST BE READING THE QUEUE FOR MESSAGES AND UPDATING THE GUI RIGHT
           // System.out.println("Timer ticking");
           // boolean status=false;
            
while(!queueRev.isEmpty())
{
    TiledMessage message=null;
    message=queueRev.remove();
    if (message.getMessagetype() == 10) 
	{//probably a status message with all colors included...change slider values accordingly...
               //System.out.println("Statys message recieved yo");
                byte[] intense=message.getIntensity();
		int[] intensity=new int[6];
                for(int i=0;i<6;i++)
                {
                    intensity[i]=intense[i]& 0xff;
                }
                System.out.println(intensity[0]+" "+intensity[1]+" "+intensity[2]+" "+intensity[3]+" "+intensity[4]+" "+intensity[5]+" ");
                jSlider1.setValue(intensity[0]);
                jSlider2.setValue(intensity[1]);
                jSlider3.setValue(intensity[2]);
                jSlider4.setValue(intensity[3]);
                jSlider5.setValue(intensity[4]);
                jSlider6.setValue(intensity[5]);
                
		if (!message.isState()) {
		//have another panel represent the state of all leds..
                jButton2.setText("OFF");
		} 
		else {
		//change to another color that thing...
                    jButton2.setText("ON");
                    
		}
		byte dimspeed=message.getDimspeed();
               // jSlider7.setValue(dimspeed);
                
	} 
	else if (message.getMessagetype() == 7) {
		if (!message.isState()) {
		//change color here
                    jButton2.setText("OFF");
		} 
		else {
	//change color here
                    jButton2.setText("ON");
		}
	}
	else if ((message.getMessagetype() > 0) && (message.getMessagetype() < 7)) {
		byte intensity[] = message.getIntensity();
                switch(message.getMessagetype()){
                    case 1:
                        jSlider1.setValue(intensity[message.getMessagetype()-1]);
                        break;
                    case 2:
                        jSlider2.setValue(intensity[message.getMessagetype()-1]);
                        break;
                    case 3:
                        jSlider3.setValue(intensity[message.getMessagetype()-1]);
                        break;
                    case 4:
                        jSlider4.setValue(intensity[message.getMessagetype()-1]);
                        break;
                    case 5:
                        jSlider5.setValue(intensity[message.getMessagetype()-1]);
                        break;
                    case 6:
                        jSlider6.setValue(intensity[message.getMessagetype()-1]);
                        break;    
                      
                }
		//change color of intensity[message.getMessagetype()-1]
	} 
	else if (message.getMessagetype() == 8) {
		//jSlider7.setValue(message.getDimspeed());
	} 
	else if (message.getMessagetype() == 11) {
		//death message sent here...do what you gotta do bro.
            jPanel1.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "Connection to the Tiled Device Lost.Please Update IP or press submit again if fine.", "Connection Lost", JOptionPane.WARNING_MESSAGE);
         //   status=true;
	}
 
}



}
    });
        t.setInitialDelay(500);
        t.setRepeats(true);
       t.start();
        
    }
                

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider(0,255,32);
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jSlider4 = new javax.swing.JSlider();
        jSlider5 = new javax.swing.JSlider();
        jSlider6 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jSlider1.setMaximum(255);
        jSlider1.setValue(32);
        jSlider1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider1MouseReleased(evt);
            }
        });
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jSlider2.setMaximum(255);
        jSlider2.setValue(32);
        jSlider2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider2MouseReleased(evt);
            }
        });
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider3.setMaximum(255);
        jSlider3.setValue(32);
        jSlider3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider3MouseReleased(evt);
            }
        });
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider3StateChanged(evt);
            }
        });

        jSlider4.setMaximum(255);
        jSlider4.setValue(32);
        jSlider4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider4MouseReleased(evt);
            }
        });
        jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider4StateChanged(evt);
            }
        });

        jSlider5.setMaximum(255);
        jSlider5.setValue(32);
        jSlider5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider5MouseReleased(evt);
            }
        });
        jSlider5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider5StateChanged(evt);
            }
        });

        jSlider6.setMaximum(255);
        jSlider6.setValue(32);
        jSlider6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSlider6MouseReleased(evt);
            }
        });
        jSlider6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider6StateChanged(evt);
            }
        });

        jLabel1.setText("RED");

        jLabel2.setText("GREEN");

        jLabel3.setText("BLUE");

        jLabel4.setText("AMBER");

        jLabel5.setText("WWHITE");

        jLabel6.setText("CWHITE");

        jLabel8.setText("SIMPLE TILED CONTROL");

        jLabel9.setText("32");

        jLabel10.setText("32");

        jLabel11.setText("32");

        jLabel12.setText("32");

        jLabel13.setText("32");

        jLabel14.setText("32");

        jLabel16.setText("IP ADDRESS");

        jTextField1.setText("Enter IP Address");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        jButton2.setText("OFF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Help");

        jMenuItem1.setText("Instructions");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jButton2)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel9)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //first check if the string is a correct ip address or not...if not send a dialog box stating the obvious..if it is then go ahead with thread spawning and message forwarding yo..
        t.stop();
        
        String PATTERN
                = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        String ip = jTextField1.getText();
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()) {
            //correct ip address detected...go ahead with thread spawning and shit...also have a green signal or ok to denote that somewhere in GUI
            System.out.println("Validation passed...checking the device now");
            if (check_ip(ip)) {
                //also check if there is any alive thread..taht gotta be taken care of too..
//                if(tilethread.isAlive())
//                {
//                    tilethread.interrupt();
//                }
                //new thread to be spawned here probably....
                TileThreads tiled = new TileThreads(queueRev, queueFwd, SerialNo, IpAddress);
                tilethread = new Thread(tiled);
                tilethread.start();
            }

           // TileThreads tile=new TileThreads();
            //Thread t=new Thread(tile);
            // t.start();
        } else {
            System.out.println("Incorrect IP");
            //dialog box telling that validating failed.
            JOptionPane.showMessageDialog(null, "IP Validation FAILED", "Validation Failed", JOptionPane.WARNING_MESSAGE);
            jPanel1.setBackground(Color.red);
           // jOptionPane1.setVisible(true);

        }
        t.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
       // t.stop();
        jLabel9.setText("" + jSlider1.getValue());
        
//        if((tilethread.isAlive())&&(!jSlider1.getValueIsAdjusting()))
//        {
//            TiledMessage tile=new TiledMessage(SerialNo);
//            byte intensity[]=new byte[6];
//            intensity[0]=(byte) jSlider1.getValue();
//            tile.setMessagetype((byte)1);
//            tile.setIntensity(intensity);
//            queueFwd.add(tile);
//        }
       // t.start();
    }//GEN-LAST:event_jSlider1StateChanged

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        // TODO add your handling code here:
        jLabel10.setText("" + jSlider2.getValue());
//        if((tilethread.isAlive())&&(!jSlider2.getValueIsAdjusting()))
//        {
//            TiledMessage tile=new TiledMessage(SerialNo);
//            byte intensity[]=new byte[6];
//            intensity[1]=(byte) jSlider2.getValue();
//            tile.setMessagetype((byte)2);
//            tile.setIntensity(intensity);
//            queueFwd.add(tile);
//        }
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider3StateChanged
        // TODO add your handling code here:
        jLabel11.setText("" + jSlider3.getValue());
//        if((tilethread.isAlive())&&(!jSlider3.getValueIsAdjusting()))
//        {
//            TiledMessage tile=new TiledMessage(SerialNo);
//            byte intensity[]=new byte[6];
//            intensity[2]=(byte) jSlider3.getValue();
//            tile.setMessagetype((byte)3);
//            tile.setIntensity(intensity);
//            queueFwd.add(tile);
//        }
    }//GEN-LAST:event_jSlider3StateChanged

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider4StateChanged
        // TODO add your handling code here:
        jLabel12.setText("" + jSlider4.getValue());
//        if((tilethread.isAlive())&&(!jSlider4.getValueIsAdjusting()))
//        {
//            TiledMessage tile=new TiledMessage(SerialNo);
//            byte intensity[]=new byte[6];
//            intensity[3]=(byte) jSlider4.getValue();
//            tile.setMessagetype((byte)4);
//            tile.setIntensity(intensity);
//            queueFwd.add(tile);
//        }
    }//GEN-LAST:event_jSlider4StateChanged

    private void jSlider5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider5StateChanged
        // TODO add your handling code here:
        jLabel13.setText("" + jSlider5.getValue());
//        if((tilethread.isAlive())&&(!jSlider5.getValueIsAdjusting()))
//        {
//            TiledMessage tile=new TiledMessage(SerialNo);
//            byte intensity[]=new byte[6];
//            intensity[4]=(byte) jSlider5.getValue();
//            tile.setMessagetype((byte)5);
//            tile.setIntensity(intensity);
//            queueFwd.add(tile);
//        }
    }//GEN-LAST:event_jSlider5StateChanged

    private void jSlider6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider6StateChanged
        // TODO add your handling code here
        jLabel14.setText("" + jSlider6.getValue());
//        if((tilethread.isAlive())&&(!jSlider6.getValueIsAdjusting()))
//        {
//            TiledMessage tile=new TiledMessage(SerialNo);
//            byte intensity[]=new byte[6];
//            intensity[5]=(byte) jSlider6.getValue();
//            tile.setMessagetype((byte)6);
//            tile.setIntensity(intensity);
//            queueFwd.add(tile);
//        }
    }//GEN-LAST:event_jSlider6StateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JTextPane jtp = new JTextPane();
        Document doc = jtp.getDocument();
        String s = "Please connect the tiled device to the local network and note down the ip address assigned to the Tiled Device.Enter the ip address in iPv4 format in the text field and press SUBMIT.once the Tiled device is connected the upper right corner will change color from Red to Green and dialog box will pop up.After it turns green,you can change the brightness and the state of the device by using the appropriate sliders.There are still a few bugs in this version as it is only meant as a minimum control version of the original software.Please contact me at vipula@bu.edu if any there are any questions regarding the usage of the software.Also I can make changes or add features if need be.";
        try {
            doc.insertString(0, s, null);
        } catch (BadLocationException ex) {
            Logger.getLogger(MFrondEnd.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtp.setSize(new Dimension(480, 10));
        jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
        JOptionPane.showMessageDialog(null, jtp, "Instructions", JOptionPane.PLAIN_MESSAGE);


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(jButton2.getText().compareToIgnoreCase("OFF")==0)
        {
            if(tilethread.isAlive())
            {
                jButton2.setText("ON");
                TiledMessage tile=new TiledMessage(SerialNo);
            tile.setMessagetype((byte)7);
            tile.setState(true);
            queueFwd.add(tile);
        
                
            }
            
        }
        else
        {
            if(tilethread.isAlive())
            {
                jButton2.setText("OFF");
                TiledMessage tile=new TiledMessage(SerialNo);
            tile.setMessagetype((byte)7);
            tile.setState(false);
            queueFwd.add(tile);
        
                
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSlider1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider1MouseReleased
        // TODO add your handling code here:
        System.out.println("Triggering mouse released");
                if((tilethread.isAlive()))
        {
            TiledMessage tile=new TiledMessage(SerialNo);
            byte intensity[]=new byte[6];
            intensity[0]=(byte) jSlider1.getValue();
            tile.setMessagetype((byte)1);
            tile.setIntensity(intensity);
            queueFwd.add(tile);
        }
        
    }//GEN-LAST:event_jSlider1MouseReleased

    private void jSlider2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider2MouseReleased
        // TODO add your handling code here:
        System.out.println("Triggering mouse released");
                if((tilethread.isAlive()))
        {
            TiledMessage tile=new TiledMessage(SerialNo);
            byte intensity[]=new byte[6];
            intensity[1]=(byte) jSlider2.getValue();
            tile.setMessagetype((byte)2);
            tile.setIntensity(intensity);
            queueFwd.add(tile);
        }
    }//GEN-LAST:event_jSlider2MouseReleased

    private void jSlider3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider3MouseReleased
        // TODO add your handling code here:
        System.out.println("Triggering mouse released");
                if((tilethread.isAlive()))
        {
            TiledMessage tile=new TiledMessage(SerialNo);
            byte intensity[]=new byte[6];
            intensity[2]=(byte) jSlider3.getValue();
            tile.setMessagetype((byte)3);
            tile.setIntensity(intensity);
            queueFwd.add(tile);
        }
    }//GEN-LAST:event_jSlider3MouseReleased

    private void jSlider4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider4MouseReleased
        // TODO add your handling code here:
        System.out.println("Triggering mouse released");
                if((tilethread.isAlive()))
        {
            TiledMessage tile=new TiledMessage(SerialNo);
            byte intensity[]=new byte[6];
            intensity[3]=(byte) jSlider4.getValue();
            tile.setMessagetype((byte)4);
            tile.setIntensity(intensity);
            queueFwd.add(tile);
        }
    }//GEN-LAST:event_jSlider4MouseReleased

    private void jSlider5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider5MouseReleased
        // TODO add your handling code here:
        System.out.println("Triggering mouse released");
                if((tilethread.isAlive()))
        {
            TiledMessage tile=new TiledMessage(SerialNo);
            byte intensity[]=new byte[6];
            intensity[4]=(byte) jSlider5.getValue();
            tile.setMessagetype((byte)5);
            tile.setIntensity(intensity);
            queueFwd.add(tile);
        }
    }//GEN-LAST:event_jSlider5MouseReleased

    private void jSlider6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider6MouseReleased
        // TODO add your handling code here:
        System.out.println("Triggering mouse released");
                if((tilethread.isAlive()))
        {
            TiledMessage tile=new TiledMessage(SerialNo);
            byte intensity[]=new byte[6];
            intensity[5]=(byte) jSlider6.getValue();
            tile.setMessagetype((byte)6);
            tile.setIntensity(intensity);
            queueFwd.add(tile);
        }
    }//GEN-LAST:event_jSlider6MouseReleased

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MFrondEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        final MFrondEnd m = new MFrondEnd();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                m.setVisible(true);
            }
        });

    }
    private final int ipstatus;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JSlider jSlider5;
    private javax.swing.JSlider jSlider6;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private boolean check_ip(String ip) {
        System.out.println("CHECKING THE DEVICE NOW");
        boolean status = false;
        try {
            final byte[] message = new byte[]{0x7E, 0x04, 0x00, 0x00, 0x00, 0x7A};
            SocketConnection sock = new SocketConnection(InetAddress.getByName(ip), 8080);
            //String hex = DatatypeConverter.printHexBinary(message);
            //System.out.println("Sent message is" + hex);

            if (sock.isConnected() == 1) {
                System.out.println("Connected to ip" + ip);
                byte rmesg[] = null;

                rmesg = sock.snd_recv(message);
                if (rmesg == null) {
                    System.out.println("Not the right device...Skip me");
                } else if (rmesg.length > 0) {
                    //	String hex1 = DatatypeConverter.printHexBinary(rmesg);
                    //	System.out.println("Recieved message is" + hex1);

                    MessageDecoder decode = new MessageDecoder(rmesg);
                    //System.out.println(decode.message_decode());
                    status = true;
                    if (decode.message_decode() == 1) {
                        SerialNo = decode.SerialNo;
                        IpAddress = decode.ip;
                        System.out.println("SerialNo is " + decode.SerialNo);
                        System.out.println("IP ADDRESS is " + decode.ip.toString());

                    }

                } else {
                    System.out.println("Nothing recieved");

                }
            }

            if (status) {
                JOptionPane.showMessageDialog(null, "Tiled Device found at the IP Address", "", JOptionPane.INFORMATION_MESSAGE);
                jPanel1.setBackground(Color.GREEN);
            } else {
                JOptionPane.showMessageDialog(null, "NO Tiled Device found at the IP Address", "ERROR", JOptionPane.WARNING_MESSAGE);
                jPanel1.setBackground(Color.RED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MFrondEnd.class.getName()).log(Level.SEVERE, null, ex);

        }
        return status;
    }
}
