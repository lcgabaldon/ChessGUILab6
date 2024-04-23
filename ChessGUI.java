import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exceptions.*;
import figures.*;
import interfaces.*;

public class ChessGUI extends JFrame implements ActionListener {

    private JPanel mainPanel; // the outside
    private JPanel boardPanel; // the specific board
    private JPanel controlPanel; // where the buttons are on the left
    private Tile[][] boardCells;

    public ChessGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Lab 6: Chess!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1040);

        mainPanel = new JPanel(new BorderLayout());
        boardPanel = new JPanel(new GridLayout(8, 8));
        controlPanel = new JPanel(new FlowLayout());
        controlPanel.setLayout(new BoxLayout(controlPanel, 1));
        initializeBoard();
        initializeButtons();

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

    }

    private void initializeButtons() {
        // Define arrays for dropdowns
        String[] petStrings = { "Pawn", "Rook", "Bishop", "Knight", "Queen", "King", "--" };
        String[] petColors = { "White", "Black", "--" };
        String[] petColumns = { "A", "B", "C", "D", "E", "F", "G", "H", "--" };
        String[] petRanks = { "1", "2", "3", "4", "5", "6", "7", "8", "--" };

        JLabel labelPieceName = new JLabel("Select Piece:");
        JComboBox<String> pieceList = new JComboBox<>(petStrings);
        JPanel piecePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pieceList.setSelectedIndex(petStrings.length - 1);
        piecePanel.add(labelPieceName);
        piecePanel.add(pieceList);

        JLabel labelColor = new JLabel("Select Color:");
        JComboBox<String> colorList = new JComboBox<>(petColors);
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colorList.setSelectedIndex(petColors.length - 1);
        colorPanel.add(labelColor);
        colorPanel.add(colorList);

        JLabel labelPosX = new JLabel("Select Column:");
        JComboBox<String> columnList = new JComboBox<>(petColumns);
        JPanel columnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        columnList.setSelectedIndex(petColumns.length - 1);
        columnPanel.add(labelPosX);
        columnPanel.add(columnList);

        JLabel labelPosY = new JLabel("Select Rank:");
        JComboBox<String> rankList = new JComboBox<>(petRanks);
        JPanel rankPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rankList.setSelectedIndex(petRanks.length - 1);
        rankPanel.add(labelPosY);
        rankPanel.add(rankList);

        JButton OKButton = new JButton("Select");
        OKButton.setFont(new Font("Arial", Font.BOLD, 16)); // Enhance button's font
        OKButton.setPreferredSize(new Dimension(100, 40)); // Set preferred size
        OKButton.addActionListener(this);// register an action listener

        // Set up the control panel for horizontal layout
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(piecePanel);
        controlPanel.add(colorPanel);
        controlPanel.add(columnPanel);
        controlPanel.add(rankPanel);
        controlPanel.add(OKButton);

        // Ensure the panel does not expand its components to fill space
        controlPanel.add(Box.createHorizontalGlue());

        // Add the control panel to the main frame
        add(controlPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initializeBoard() {
        boardCells = new Tile[8][8];
        Color lightGray = new Color(240, 240, 240);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color backgroundColor = (row + col) % 2 == 0 ? lightGray : Color.BLACK;
                Tile tile = new Tile(backgroundColor);
                boardCells[row][col] = tile;
                boardPanel.add(tile);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Handle the event
        // call your verify move method
        JOptionPane.showMessageDialog(new JFrame(),
                "You have selected a piece");
    }
}
