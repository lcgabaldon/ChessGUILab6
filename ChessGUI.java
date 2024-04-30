import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exceptions.*;
import figures.*;
import interfaces.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChessGUI extends JFrame implements ActionListener {

    private JPanel mainPanel; // the outside
    private JPanel boardPanel; // the specific board
    private JPanel controlPanel; // where the buttons are on the left
    private Tile[][] boardCells;
    private Map<String, ImageIcon> pieceImages;
    private Set<String> placedPieces = new HashSet<>();
    private JComboBox<String> pieceList;

    public ChessGUI() {
        initializePictures();
        initializeUI();
    }

    private void initializePictures() {
        pieceImages = new HashMap<>();
        String[] pieces = { "pawn", "knight", "bishop", "rook", "queen", "king" };
        String[] colors = { "white", "black" };

        for (String color : colors) {
            for (String piece : pieces) {
                String filename = "PiecePictures/" + color + "_" + piece + ".gif";
                ImageIcon image = new ImageIcon(filename);
                pieceImages.put(color + "_" + piece, image);
            }
        }
    }

    public ImageIcon getImageIcon(String pieceName) {
        return pieceImages.get(pieceName);
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

        JButton addButton = new JButton("Select");
        addButton.setFont(new Font("Arial", Font.BOLD, 16)); // Enhance button's font
        addButton.setPreferredSize(new Dimension(100, 40)); // Set preferred size
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPieceType = pieceList.getSelectedItem().toString();
                String selectedColor = colorList.getSelectedItem().toString();
                String selectedColumn = columnList.getSelectedItem().toString();
                String selectedRank = rankList.getSelectedItem().toString();

                if ("--".equals(selectedPieceType) || "--".equals(selectedColor) ||
                        "--".equals(selectedColumn) || "--".equals(selectedRank)) {
                    JOptionPane.showMessageDialog(null, "Please make sure to select a piece, color, column, and rank.",
                            "Incomplete Selection", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int column = selectedColumn.charAt(0) - 'A';
                int rank = 8 - Integer.parseInt(selectedRank);
                String key = selectedColor.toLowerCase() + "_" + selectedPieceType.toLowerCase();

                if (!placedPieces.contains(key)) {
                    ImageIcon icon = getImageIcon(key);
                    boardCells[rank][column].setPieceIcon(icon);
                    boardCells[rank][column].revalidate();
                    boardCells[rank][column].repaint();
                    placedPieces.add(key); // Mark this piece as placed

                    // Ask for target position
                    String targetPosition = JOptionPane.showInputDialog("Enter target position (e.g., A5):");
                    if (targetPosition != null && targetPosition.length() == 2) {
                        int targetCol = targetPosition.toUpperCase().charAt(0) - 'A';
                        int targetRow = 8 - Character.getNumericValue(targetPosition.charAt(1));

                        Figure piece = createPiece(PieceType.valueOf(selectedPieceType.toUpperCase()),
                                Colorr.valueOf(selectedColor.toUpperCase()),
                                Column.valueOf(selectedColumn), rank);

                        if (piece.moveTo(Column.values()[targetCol], targetRow)) {
                            boardCells[rank][column].setPieceIcon(null); // Clear old icon
                            boardCells[targetRow][targetCol].setPieceIcon(icon);
                            boardCells[targetRow][targetCol].revalidate();
                            boardCells[targetRow][targetCol].repaint();
                            JOptionPane.showMessageDialog(null, "The piece can move to " + targetPosition + ".",
                                    "Valid Move", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The piece cannot move to " + targetPosition + ".",
                                    "Invalid Move", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This piece has already been placed. Please choose another.",
                            "Piece Already Placed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16)); // Keep the style consistent with the Select button
        clearButton.setPreferredSize(new Dimension(100, 40)); // Same dimensions for consistency
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all pieces from the board cells
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        boardCells[row][col].clearPieceIcon();
                    }
                }

                // Clear the set that tracks placed pieces
                placedPieces.clear();

                // Reset the dropdown for pieces using the outer class reference
                ChessGUI.this.resetPieceList(pieceList);
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(piecePanel);
        controlPanel.add(colorPanel);
        controlPanel.add(columnPanel);
        controlPanel.add(rankPanel);
        controlPanel.add(addButton);
        controlPanel.add(clearButton);

        // Ensure the panel does not expand its components to fill space
        controlPanel.add(Box.createHorizontalGlue());

        // Add the control panel to the main frame
        add(controlPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void resetPieceList(JComboBox<String> pieceList) {
        pieceList.removeAllItems();
        String[] pieces = { "Pawn", "Rook", "Bishop", "Knight", "Queen", "King", "--" };
        for (String piece : pieces) {
            pieceList.addItem(piece);
        }
        pieceList.setSelectedIndex(pieces.length - 1);
    }

    private void initializeBoard() {
        boardCells = new Tile[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color backgroundColor = (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY;
                Tile tile = new Tile(backgroundColor);
                boardCells[row][col] = tile;
                boardPanel.add(tile);
            }
        }
    }

    private Figure createPiece(PieceType type, Colorr color, Column column, int row) {
        switch (type) {
            case PAWN:
                return new Pawn(type, color, column, row);
            case KNIGHT:
                return new Knight(type, color, column, row);
            case BISHOP:
                return new Bishop(type, color, column, row);
            case ROOK:
                return new Rook(type, color, column, row);
            case QUEEN:
                return new Queen(type, color, column, row);
            case KING:
                return new King(type, color, column, row);
            default:
                return null;
        }
    }

}
