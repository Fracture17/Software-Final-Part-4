import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GUIDisplay extends JFrame implements Display {
    @Override
    public void update(Board board) {

    }

    @Override
    public void displayMove(Move move) {

    }

    @Override
    public void askForPieceToMove(Board board, Piece.color playerColor) {

    }

    @Override
    public Position getSourcePiecePosition(Board board, Piece.color playerColor) {
        return null;
    }

    @Override
    public void askWhereToMove(Board board, Piece.color playerColor, ArrayList<Move> validMoves) {

    }

    @Override
    public Position getDestinationPosition(Board board, Piece.color playerColor) {
        return null;
    }



    public GUIDisplay() {
        JPanel chessBoard = new JPanel(new GridLayout(0, 9)) {

            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };

        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                JButton square = new JButton();
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                square.setIcon(icon);

                if((r + c) % 2 == 1) {
                    square.setBackground(Color.WHITE);
                }
                else {
                    square.setBackground(Color.BLACK);
                }

                chessBoardSquares[r][c] = square;
            }
        }


        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int c = 0; c < 8; c++) {
            chessBoard.add(
                    new JLabel("" + (char)('a' + c), SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if(c == 0) {
                    chessBoard.add(new JLabel("" + (9-(r + 1)),
                            SwingConstants.CENTER));
                }
                chessBoard.add(chessBoardSquares[r][c]);
            }
        }

        add(chessBoard, "North");


        // Function to set visible
        // status of JFrame.
        setVisible(true);

        // this Keyword refers to current
        // object. Function to set size of JFrame.
        this.setSize(400, 180);
    }

    private JButton[][] chessBoardSquares = new JButton[8][8];
}

