package slidergamefinal;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SliderGame3x3 extends JFrame implements ActionListener {

        private JFrame myFrame;
        private JPanel gameboardPanel; //create a panel to add to JFRAME
	private JButton[] buttons = new JButton[9];  //Make room for 16 button objects
        public int numMoves = 0;
	private int emptyIndex = 8; //Variable will track the empty spot
        private boolean check[] = new boolean[8];
	
        public static void main(String[] args) {

            new SliderGame3x3();      //Run constructor for class
	}
        
	public SliderGame3x3()
	{
            
		myFrame = new JFrame("Slider Practice GUI");
                myFrame.setSize(400,400);   //Overall size of grid but window
                                            //is resizable by default. Layout
                                            //Manager takes care of the necessary scaling.
                myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font f = new Font("Arial", Font.BOLD, 26);
		Color[] colours = {Color.orange, Color.magenta};
		
		gameboardPanel = new JPanel();
                gameboardPanel.setEnabled(false);
		gameboardPanel.setLayout(new GridLayout(3,3,5,5)); // 4x4 grid with 5 pixel padding										   // vert/horz dividers
		gameboardPanel.setBackground(Color.black); //Allows empty space to be black
		Random scrambler = new Random();

       
                
		for (int i = 0; i < buttons.length-1; i++)  //From i is 0 to 15
		{
                    int placeHolder = scrambler.nextInt(8);
			int colourIndex = 0;  //Start with Orange
			if(check[placeHolder] == false){
			buttons[i] = new JButton("" + placeHolder);  //Constructor sets text on new buttons
			buttons[i].setSize(100,100);  //Button size, but don't really need this line
						      //line since we are using Layout Manager
			colourIndex = 0; //default colour of square is orange
                        if ( (i%2 != 0)) 
                        {
                            colourIndex = 1; //make white if necessary
                        }

			buttons[i].setBackground(colours[colourIndex]);
			buttons[i].setForeground(Color.blue);   //Text colour
			buttons[i].setFont(f);
			buttons[i].addActionListener(this);   //Set up ActionListener on each button
			gameboardPanel.add(buttons[i]);       //Add buttons to the grid layout of 
				check[placeHolder] = true;			      //gameboardPanel
		}else i--;
                }
		
                buttons[8] = new JButton(""+ 8);
                buttons[8].setSize(100,100);
                //buttons[8] = buttons[emptyIndex];
		int colourIndex = 0;
                buttons[8].setBackground(colours[colourIndex]);
			buttons[8].setForeground(Color.blue);   //Text colour
			buttons[8].setFont(f);
			buttons[8].addActionListener(this);   //Set up ActionListener on each button
			gameboardPanel.add(buttons[8]);       //Add buttons to the grid layout of 
                buttons[8].setVisible(false);  //Will show the black background without a
						//visible button here
		gameboardPanel.setEnabled(true);
                
                myFrame.setContentPane(gameboardPanel); //Add gameboardPanel to JFrame
                myFrame.setVisible(true); //Turn on JFrame
	}
	
        @Override
	public void actionPerformed(ActionEvent e) {
            numMoves++;
            System.out.println(numMoves);
            for(int i=0; i<buttons.length; i++){
             if(e.getSource()==buttons[i])    
                swapPieces(i);
            }
            
        }
        
        public int getNumMoves(){
            return numMoves;
        }
        
        public void scrambler (){
            
            int []buttons = new int[9];
            
            for (int i=0; i<100; i++){
                buttons[i] = (int)(1+9*Math.random());
                swapPieces(i);

            }
        }
        
         public void setForSwap(int btnIndex){
             buttons[emptyIndex].setText(buttons[btnIndex].getText());  //Move over text
		buttons[emptyIndex].setVisible(true);                      //to blank button
		buttons[btnIndex].setVisible(false); //Turn off visibility of button that was pushed
						     //and background will show through as black
		//buttons[emptyIndex].setVisible(true);//Turn on visibility of the old blank button
		emptyIndex = btnIndex;		     //Update the emptyIndex to the button that was
                                                     //pushed.
         }
        
        
        private void swapPieces(int btnIndex)   //Send along button that was pushed
	{
                if((emptyIndex+1) % 3 == 0){
                    if(emptyIndex-3==btnIndex||emptyIndex+3==btnIndex||emptyIndex-1==btnIndex){
                        setForSwap(btnIndex);
                    }
                }
                
                else if(emptyIndex % 3 == 0){
                    if(emptyIndex-3==btnIndex||emptyIndex+3==btnIndex||emptyIndex+1==btnIndex){
                        setForSwap(btnIndex);                
                    }
                }
                
                else{
                    if(emptyIndex-1==btnIndex||emptyIndex+1==btnIndex||emptyIndex-3==btnIndex||emptyIndex+3==btnIndex){
                        setForSwap(btnIndex);          
                    }
                }
            
            
		
	}	
	
}