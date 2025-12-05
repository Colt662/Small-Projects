using System;
using System.Drawing;
using System.Windows.Forms;

namespace Colt_Youngblood_Final_Exam;

public static class ColtYoungbloodFinalExam
{
    //Constants
    private const int ClassAPrice = 15;
    private const int ClassBPrice = 12;
    private const int ClassCPrice = 9;
    
    //Running totals
    private static int _revenueSum;
    private static int _ticketSum;
    private static int _transactionSum;
    
    //parts that are modified/used by buttons
    
    //inputs
    private static TextBox? _classAInput;
    private static TextBox? _classBInput;
    private static TextBox? _classCInput;
    
    //outputs
    //revenue
    private static Label? _classARevenueOutput;
    private static Label? _classBRevenueOutput;
    private static Label? _classCRevenueOutput;
    private static Label? _totalRevenueOutput;
    //tickets
    private static Label? _totalTicketOutput;
    //summary
    private static Label? _revenueSummaryOutput;
    private static Label? _ticketSummaryOutput;
    private static Label? _transactionSummaryOutput;
    
    
    public static void Main()
    {
        //layout variables
        //labels
        int labelXSize = 100;
        int labelYSize = 23;
        int labelXGap = 15;
        int labelYGap = 10;
        Size labelSize = new Size(labelXSize, labelYSize);
        
        //inputs/outputs (they use same gaps and YSize as labels)
        int inputOutputXSize = 185;
        Size inputOutputSize = new Size(inputOutputXSize, labelYSize);
        
        //regular group boxes (5x2)
        int groupBoxTopPadding = 20;
        int groupBoxXSize = (labelXGap * 3) +  labelXSize + inputOutputXSize;
        int groupBoxYSize = groupBoxTopPadding + (labelYSize * 5) + (labelYGap * 5);
        int groupBoxXGap = 5;
        int groupBoxYGap = 10;
        Size groupBoxSize = new Size(groupBoxXSize, groupBoxYSize);
        
        //small group boxes (3x2)
        int smallGroupBoxYSize = groupBoxTopPadding + (labelYGap * 3) + (labelYSize * 3);
        Size smallGroupBoxSize = new Size(groupBoxXSize, smallGroupBoxYSize);
        
        //Buttons
        int buttonXGap = 15;
        int buttonXSize = (int)((groupBoxXSize / 3.0) - (2 * buttonXGap / 3.0)); 
        Size buttonSize = new Size(buttonXSize, smallGroupBoxYSize);
        
        //whole form (2x2, large boxes on top, small on bottom)
        int formXSize = (3 * groupBoxXGap) + (2 * groupBoxXSize);
        int formYSize = (3 * groupBoxYGap) + groupBoxYSize + smallGroupBoxYSize;
        Size formSize = new Size(formXSize, formYSize);
        
        
        
        //Preparing form
        Form stadiumForm = new Form();
        stadiumForm.Text = "Stadium Seating Cost and Revenue";
        stadiumForm.ClientSize = formSize;
        
        //
        //
        //ticket/input box 
        //
        //
        //initializing
        Label ticketsInstructionLabel = new Label();
        Label classAInputLabel = new Label();
        Label classBInputLabel = new Label();
        Label classCInputLabel = new Label();
        Label totalTicketLabel = new Label();
        _classAInput = new TextBox();
        _classBInput = new TextBox();
        _classCInput = new TextBox();
        _totalTicketOutput = new Label();
        //Label Text
        ticketsInstructionLabel.Text = "Enter the number of tickets sold for each class of seat.";
        classAInputLabel.Text = "Class A:";
        classBInputLabel.Text = "Class B:";
        classCInputLabel.Text = "Class C:";
        totalTicketLabel.Text = "Total Tickets:";
        //Output Label Border Style
        _totalTicketOutput.BorderStyle = BorderStyle.Fixed3D;
        //Text Alignment for inputs/outputs
        _classAInput.TextAlign = HorizontalAlignment.Right;
        _classBInput.TextAlign = HorizontalAlignment.Right;
        _classCInput.TextAlign = HorizontalAlignment.Right;
        _totalTicketOutput.TextAlign = ContentAlignment.MiddleRight;
        //Size
        ticketsInstructionLabel.Size = new Size(labelXSize + labelXGap + inputOutputXSize, labelYSize);
        classAInputLabel.Size = labelSize;
        classBInputLabel.Size = labelSize;
        classCInputLabel.Size = labelSize;
        totalTicketLabel.Size = labelSize;
        _classAInput.Size = inputOutputSize;
        _classBInput.Size = inputOutputSize;
        _classCInput.Size = inputOutputSize;
        _totalTicketOutput.Size = inputOutputSize;
        //Locations
        //layout logic: https://docs.google.com/presentation/d/18xRfjIFSAiO4AzOslEt5hncjXJEKaSE6t3IdJMrnwiM/edit?usp=sharing
        ticketsInstructionLabel.Location = new Point(labelXGap * 1,                    groupBoxTopPadding);
        classAInputLabel.Location =        new Point(labelXGap * 1,                    groupBoxTopPadding + labelYGap * 1 + labelYSize * 1);
        classBInputLabel.Location =        new Point(labelXGap * 1,                    groupBoxTopPadding + labelYGap * 2 + labelYSize * 2);
        classCInputLabel.Location =        new Point(labelXGap * 1,                    groupBoxTopPadding + labelYGap * 3 + labelYSize * 3);
        totalTicketLabel.Location =        new Point(labelXGap * 1,                    groupBoxTopPadding + labelYGap * 4 + labelYSize * 4);
        _classAInput.Location =            new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 1 + labelYSize * 1);
        _classBInput.Location =            new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 2 + labelYSize * 2);
        _classCInput.Location =            new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 3 + labelYSize * 3);
        _totalTicketOutput.Location =      new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 4 + labelYSize * 4);
        //making the ticket box and add its elements
        GroupBox ticketBox = new GroupBox();
        ticketBox.Location = new Point(groupBoxXGap, groupBoxYGap);
        ticketBox.Size = groupBoxSize;
        ticketBox.Text = "Tickets Sold";
        ticketBox.Controls.Add(ticketsInstructionLabel);
        ticketBox.Controls.Add(classAInputLabel);
        ticketBox.Controls.Add(classBInputLabel);
        ticketBox.Controls.Add(classCInputLabel);
        ticketBox.Controls.Add(totalTicketLabel);
        ticketBox.Controls.Add(_classAInput);
        ticketBox.Controls.Add(_classBInput);
        ticketBox.Controls.Add(_classCInput);
        ticketBox.Controls.Add(_totalTicketOutput);
        //add it to the form
        stadiumForm.Controls.Add(ticketBox);
        
        //
        //
        //Revenue box
        //
        //
        //initializing
        Label classARevenueLabel = new Label();
        Label classBRevenueLabel = new Label();
        Label classCRevenueLabel = new Label();
        Label totalRevenueLabel = new Label();
        _classARevenueOutput = new Label();
        _classBRevenueOutput = new Label();
        _classCRevenueOutput = new Label();
        _totalRevenueOutput = new Label();
        //Label Text
        classARevenueLabel.Text = "Class A:";
        classBRevenueLabel.Text = "Class B:";
        classCRevenueLabel.Text = "Class C:";
        totalRevenueLabel.Text = "Total Revenue:";
        //Output Label Border Style
        _classARevenueOutput.BorderStyle = BorderStyle.Fixed3D;
        _classBRevenueOutput.BorderStyle = BorderStyle.Fixed3D;
        _classCRevenueOutput.BorderStyle = BorderStyle.Fixed3D;
        _totalRevenueOutput.BorderStyle = BorderStyle.Fixed3D;
        //Text Alignment for inputs/outputs
        _classARevenueOutput.TextAlign = ContentAlignment.MiddleRight;
        _classBRevenueOutput.TextAlign = ContentAlignment.MiddleRight;
        _classCRevenueOutput.TextAlign = ContentAlignment.MiddleRight;
        _totalRevenueOutput.TextAlign = ContentAlignment.MiddleRight;
        //Size
        classARevenueLabel.Size = labelSize;
        classBRevenueLabel.Size = labelSize;
        classCRevenueLabel.Size = labelSize;
        totalRevenueLabel.Size = labelSize;
        _classARevenueOutput.Size = inputOutputSize;
        _classBRevenueOutput.Size = inputOutputSize;
        _classCRevenueOutput.Size = inputOutputSize;
        _totalRevenueOutput.Size = inputOutputSize;
        //Locations
        //layout logic: https://docs.google.com/presentation/d/18xRfjIFSAiO4AzOslEt5hncjXJEKaSE6t3IdJMrnwiM/edit?usp=sharing
        //pretending like there's an extra label above so it's aligned with other box
        classARevenueLabel.Location =   new Point(labelXGap * 1                 ,   groupBoxTopPadding + labelYGap * 1 + labelYSize * 1);
        classBRevenueLabel.Location =   new Point(labelXGap * 1                 ,   groupBoxTopPadding + labelYGap * 2 + labelYSize * 2);
        classCRevenueLabel.Location =   new Point(labelXGap * 1                 ,   groupBoxTopPadding + labelYGap * 3 + labelYSize * 3);
        totalRevenueLabel.Location =    new Point(labelXGap * 1                 ,   groupBoxTopPadding + labelYGap * 4 + labelYSize * 4);
        _classARevenueOutput.Location = new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 1 + labelYSize * 1);
        _classBRevenueOutput.Location = new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 2 + labelYSize * 2);
        _classCRevenueOutput.Location = new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 3 + labelYSize * 3);
        _totalRevenueOutput.Location =  new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 4 + labelYSize * 4);
        //making the Revenue box and add its elements
        GroupBox revenueBox = new GroupBox();
        revenueBox.Location = new Point(groupBoxXGap * 2 + groupBoxXSize, groupBoxYGap);
        revenueBox.Size = groupBoxSize;
        revenueBox.Text = "Revenue Generated";
        revenueBox.Controls.Add(classARevenueLabel);
        revenueBox.Controls.Add(classBRevenueLabel);
        revenueBox.Controls.Add(classCRevenueLabel);
        revenueBox.Controls.Add(totalRevenueLabel);
        revenueBox.Controls.Add(_classARevenueOutput);
        revenueBox.Controls.Add(_classBRevenueOutput);
        revenueBox.Controls.Add(_classCRevenueOutput);
        revenueBox.Controls.Add(_totalRevenueOutput);
        //add it to the form
        stadiumForm.Controls.Add(revenueBox);
        
        //
        //
        //Summary box
        //
        //
        //initializing
        Label revenueSummaryLabel = new Label();
        Label ticketSummaryLabel = new Label();
        Label transactionSummaryLabel = new Label();
        _revenueSummaryOutput = new Label();
        _ticketSummaryOutput = new Label();
        _transactionSummaryOutput = new Label();
        //Label Text
        revenueSummaryLabel.Text = "Sum of Revenue:";
        ticketSummaryLabel.Text = "Sum of Tickets:";
        transactionSummaryLabel.Text = "Transactions:";
        //Output Label Border Style
        _revenueSummaryOutput.BorderStyle = BorderStyle.Fixed3D;
        _ticketSummaryOutput.BorderStyle = BorderStyle.Fixed3D;
        _transactionSummaryOutput.BorderStyle = BorderStyle.Fixed3D;
        //Text Alignment for inputs/outputs
        _revenueSummaryOutput.TextAlign = ContentAlignment.MiddleRight;
        _ticketSummaryOutput.TextAlign = ContentAlignment.MiddleRight;
        _transactionSummaryOutput.TextAlign = ContentAlignment.MiddleRight;
        //Size
        revenueSummaryLabel.Size = labelSize;
        ticketSummaryLabel.Size = labelSize;
        transactionSummaryLabel.Size = labelSize;
        _revenueSummaryOutput.Size = inputOutputSize;
        _ticketSummaryOutput.Size = inputOutputSize;
        _transactionSummaryOutput.Size = inputOutputSize;
        //Locations
        //layout logic: https://docs.google.com/presentation/d/18xRfjIFSAiO4AzOslEt5hncjXJEKaSE6t3IdJMrnwiM/edit?usp=sharing
        revenueSummaryLabel.Location =       new Point(labelXGap                     ,   groupBoxTopPadding);
        ticketSummaryLabel.Location =        new Point(labelXGap                     ,   groupBoxTopPadding + labelYGap * 1 + labelYSize * 1);
        transactionSummaryLabel.Location =   new Point(labelXGap                     ,   groupBoxTopPadding + labelYGap * 2 + labelYSize * 2);
        _revenueSummaryOutput.Location =     new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding);
        _ticketSummaryOutput.Location =      new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 1 + labelYSize * 1);
        _transactionSummaryOutput.Location = new Point(labelXGap * 2 + labelXSize * 1,   groupBoxTopPadding + labelYGap * 2 + labelYSize * 2);
        //making the Summary box and add its elements
        GroupBox summaryBox = new GroupBox();
        summaryBox.Location = new Point(groupBoxXGap, groupBoxYGap * 2 + groupBoxYSize);
        summaryBox.Size = smallGroupBoxSize;
        summaryBox.Text = "Summary";
        summaryBox.Controls.Add(revenueSummaryLabel);
        summaryBox.Controls.Add(ticketSummaryLabel);
        summaryBox.Controls.Add(transactionSummaryLabel);
        summaryBox.Controls.Add(_revenueSummaryOutput);
        summaryBox.Controls.Add(_ticketSummaryOutput);
        summaryBox.Controls.Add(_transactionSummaryOutput);
        //add it to the form
        stadiumForm.Controls.Add(summaryBox);
        
        //
        //
        //Buttons
        //
        //
        //Initializing
        Button calculateButton = new Button();
        Button clearButton = new Button();
        Button exitButton = new Button();
        //Text
        calculateButton.Text = "Calculate Revenue";
        clearButton.Text = "Clear";
        exitButton.Text = "Exit";
        //Font
        Font buttonFont =  new Font(FontFamily.GenericMonospace, 11);
        calculateButton.Font = buttonFont;
        clearButton.Font = buttonFont;
        exitButton.Font = buttonFont;
        //Color
        calculateButton.BackColor = Color.LightGreen;
        clearButton.BackColor = Color.LightYellow;
        exitButton.BackColor = Color.LightCoral;
        //Size
        calculateButton.Size = buttonSize;
        clearButton.Size = buttonSize;
        exitButton.Size = buttonSize;
        //Location
        calculateButton.Location = new Point(2 * groupBoxXGap + groupBoxXSize, 2 * groupBoxYGap + groupBoxYSize);
        clearButton.Location = new Point(2 * groupBoxXGap + groupBoxXSize + buttonXGap + buttonXSize, 2 * groupBoxYGap + groupBoxYSize);
        exitButton.Location = new Point(2 * groupBoxXGap + groupBoxXSize + 2 * (buttonXGap + buttonXSize), 2 * groupBoxYGap + groupBoxYSize);
        //Actions
        calculateButton.Click += Calculate;
        clearButton.Click += Clear;
        exitButton.Click += Exit;
        //Add directly to form
        stadiumForm.Controls.Add(calculateButton);
        stadiumForm.Controls.Add(clearButton);
        stadiumForm.Controls.Add(exitButton);
        //Assign special buttons
        stadiumForm.AcceptButton = calculateButton;
        stadiumForm.CancelButton = exitButton;
        
        
        //show the form
        Application.Run(stadiumForm);
    }
    private static void Calculate(object? sender, EventArgs e)
    {
        try
        {
            if (_classAInput != null && _classBInput != null && _classCInput != null && _totalTicketOutput != null 
                && _classARevenueOutput != null && _classBRevenueOutput != null && _classCRevenueOutput != null
                &&  _totalRevenueOutput != null && _transactionSummaryOutput != null && _revenueSummaryOutput != null
                && _ticketSummaryOutput != null)
            {
                int classATickets = Convert.ToInt32(_classAInput.Text.Replace(",", ""));
                int classBTickets = Convert.ToInt32(_classBInput.Text.Replace(",", ""));
                int classCTickets = Convert.ToInt32(_classCInput.Text.Replace(",", ""));

                int classARevenue = ClassAPrice * classATickets;
                int classBRevenue = ClassBPrice * classBTickets;
                int classCRevenue = ClassCPrice * classCTickets;
                
                int totalRevenue = classARevenue + classBRevenue + classCRevenue;
                int totalTickets = classATickets + classBTickets + classCTickets;
                
                _revenueSum +=  totalRevenue;
                _ticketSum +=  totalTickets;
                _transactionSum++;
                
                
                _totalTicketOutput.Text = totalTickets.ToString("N0");
                
                _classARevenueOutput.Text = classARevenue.ToString("C");
                _classBRevenueOutput.Text = classBRevenue.ToString("C");
                _classCRevenueOutput.Text = classCRevenue.ToString("C");
                _totalRevenueOutput.Text = totalRevenue.ToString("C");
                
                _revenueSummaryOutput.Text = _revenueSum.ToString("C");
                _ticketSummaryOutput.Text = _ticketSum.ToString("N0");
                _transactionSummaryOutput.Text = _transactionSum.ToString("N0");
            }
            else
            {
                throw new Exception("Internal Error: Form field have not been set");
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }
        finally
        {
            if (_classAInput != null && _classBInput != null && _classCInput != null)//IDE gets mad without second check
            {
                _classAInput.Text = string.Empty;
                _classBInput.Text = string.Empty;
                _classCInput.Text = string.Empty;
                _classAInput.Focus();
            }
        }
    }

    private static void Clear(object? sender, EventArgs e)
    {
        if (_classAInput != null && _classBInput != null && _classCInput != null && _totalTicketOutput != null
            && _classARevenueOutput != null && _classBRevenueOutput != null && _classCRevenueOutput != null &&
            _totalRevenueOutput != null)
        {
            _classAInput.Text = string.Empty;
            _classBInput.Text = string.Empty;
            _classCInput.Text = string.Empty;
            _totalTicketOutput.Text = string.Empty;
            
            _classARevenueOutput.Text = string.Empty;
            _classBRevenueOutput.Text = string.Empty;
            _classCRevenueOutput.Text = string.Empty;
            _totalRevenueOutput.Text = string.Empty;
            
            _classAInput.Focus();
        }
    }

    private static void Exit(object? sender, EventArgs e)
    {
        Application.Exit();
    }
}