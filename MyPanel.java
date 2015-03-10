import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class MyPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private int[][] results;
	
	public MyPanel(){
		this.run();
	}
	
	private void run(){
		this.setSize(750, 750);
		this.results = new int[250][];

		for (int i = 0; i < this.results.length; i++)
			this.runTestOfN(0+i,i);
		
	}
	
	private void runTestOfN(int n, int j){
		int[] array = new int[n];
		for (int i = 0; i < array.length; i++){
			array[i] = i;
		}

		this.results[j] = new int[array.length];
		for (int i = 0; i < results[j].length; i++){
			this.results[j][i] = this.search(array, i);
		}
	}
	
	private int search(int[] array, int val){
		int mid = array.length / 2;
		if (array[mid]==val)
			return 1;
		
		if (array[mid]>val)
			return 1 + searchHelper(array, val, 0,mid-1);
		
		if (array[mid]<val)
			return 1 + searchHelper(array, val, mid+1, array.length-1);
				
		return 0;
	}
	
	private int searchHelper(int[] array, int val, int low, int high){
		int mid = (low + high)/2;
		
		if (array[mid]==val)
			return 1;
		
		if (array[mid]>val)
			return 1 + searchHelper(array, val, low,mid-1);
		
		if (array[mid]<val)
			return 1 + searchHelper(array, val, mid+1, high);
				
		return 0;
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for (int j = 0; j < this.results.length; j++){
			int max = 0;
			j += 1;
			for (int i : this.results[j]){
				if (i > max) max = i;
			}
			
			for (int i=0; i < this.results[j].length; i++){
				int n = this.results[j][i];
				double ratio = (double)n / (double)max;
				int R = ratio<0.5?(int)(255.0*2.0*ratio):255;
				int G = ratio<0.5?255:(int)(255.0 - 255.0*2.0*(ratio-0.5));
				int B = 0;
				g2.setColor(new Color(R,G,B));
				g2.fillRect((int)(750.0*(double)i/(double)this.results[j].length), j*1, (int)(750.0/(double)this.results[j].length)+1, 3);
			}
		}
	}

}
