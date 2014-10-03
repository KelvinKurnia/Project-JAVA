import javax.swing.JButton;

public class HalmaButton extends JButton
{
	private Field field = new Field();
	
	public HalmaButton()
	{
		this.setBackground(HalmaSettings.BGCOLOR);
	}
	
	public HalmaButton(Field field)
	{
		this.field = field;
		this.setToolTipText("" +field.getXPosition() + "/" + field.getYPosition());
		this.setBackground(HalmaSettings.BGCOLOR);
	}

	public Field getField() 
	{
		return field;
	}

	public void setField(Field field)
	{
		this.field = field;
	}
	
}