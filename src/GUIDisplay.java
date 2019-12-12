import Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GUIDisplay extends JFrame implements Display {
    @Override
    public void update(Board board, Piece.color currentPlayerColor) {
        resetCheckerBoard();
        setPieces(board);
    }

    @Override
    public void askForPieceToMove(Board board, Piece.color currentPlayerColor) {
        for(Position position: board.getAllPositions()) {
            if(board.getPiece(position).getColor() == currentPlayerColor) {
                if(PieceMoves.getValidMoves(board, position).size() > 0) {
                    getSquare(position).setBackground(Color.GREEN);
                }
            }
        }
    }

    @Override
    public Position getSourcePiecePosition(Board board, Piece.color currentPlayerColor) {
        while(true) {
            Position result = getButtonPressPosition();
            if(board.getPiece(result) != null) {
                if(board.getPiece(result).getColor() == currentPlayerColor) {
                    resetCheckerBoard();
                    setPieces(board);
                    return result;
                }
            }
        }
    }

    @Override
    public void askWhereToMove(Board board, Piece.color currentPlayerColor, ArrayList<Move> validMoves) {
        if(validMoves.size() == 0) {
            return;
        }
        getSquare(validMoves.get(0).sourcePos).setBackground(Color.BLUE);

        for(Move move: validMoves) {
            getSquare(move.destPos).setBackground(Color.GREEN);
        }
    }

    @Override
    public Position getDestinationPosition(Board board, Piece.color currentPlayerColor) {
        Position result = getButtonPressPosition();
        resetCheckerBoard();
        setPieces(board);
        return result;
    }

    private Position getButtonPressPosition() {
        //Might be a race condition if user presses buttons too quickly, not too worried
        //Shouldn't be an issue for the application's limited scope, and also should be
        //relatively easy to identify and fix if it becomes a problem
        lastButtonPressPosition = null;
        while (lastButtonPressPosition == null) {
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {

            }
        }
        return lastButtonPressPosition;
    }

    @Override
    public void displayWinner(Piece.color winningColor) {
        if(winningColor == Piece.color.WHITE) {
            JFrame winningMessage = new JFrame("White wins!");
            winningMessage.setVisible(true);
        }
        else {
            JFrame winningMessage = new JFrame("Black wins!");
            winningMessage.setVisible(true);
        }
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
                } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = (Math.min(w, h));
                return new Dimension(s,s);
            }
        };

        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                final int y = r;
                final int x = c;
                JButton square = new JButton();
                square.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        lastButtonPressPosition = new Position(y, x);
                    }
                });

                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                square.setIcon(icon);

                chessBoardSquares[r][c] = square;
            }
        }

        resetCheckerBoard();

        try {
            final BufferedImage source = ImageIO.read(new File("src/chess pieces.png"));
            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 6; c++) {
                    pieceImages[r][c] = source.getSubimage(c * 64, r * 64, 64, 64);
                }
            }
        }
        catch (Exception ignored) {

        }

        chessBoard.add(new JLabel(""));
        for (int c = 0; c < 8; c++) {
            chessBoard.add(
                    new JLabel("" + (char)('a' + c), SwingConstants.CENTER));
        }
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

        setVisible(true);

        this.setSize(400, 180);
    }

    private void resetCheckerBoard() {
        for(int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                chessBoardSquares[r][c].setIcon(icon);

                if ((r + c) % 2 == 1) {
                    chessBoardSquares[r][c].setBackground(Color.WHITE);
                } else {
                    chessBoardSquares[r][c].setBackground(Color.BLACK);
                }
            }
        }
    }

    private void setPieces(Board board) {
        for(Position position: board.getAllPositions()) {
            Piece piece = board.getPiece(position);
            if (piece.getType() != Piece.type.NONE) {
                getSquare(position).setIcon(new ImageIcon(getPieceImage(piece)));
            }
        }
    }

    private BufferedImage getPieceImage(Piece piece) {
        int pieceColor = piece.getColor() == Piece.color.BLACK ? 0 : 1;
        switch(piece.getType()) {
            case KING:
                return pieceImages[pieceColor][0];
            case QUEEN:
                return pieceImages[pieceColor][1];
            case ROOK:
                return pieceImages[pieceColor][2];
            case KNIGHT:
                return pieceImages[pieceColor][3];
            case BISHOP:
                return pieceImages[pieceColor][4];
            case PAWN:
                return pieceImages[pieceColor][5];
            default:
                return new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        }
    }

    private JButton getSquare(Position position) {
        return chessBoardSquares[position.getRow()][position.getCol()];
    }

    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Position lastButtonPressPosition = null;
    private BufferedImage[][] pieceImages = new BufferedImage[2][6];
}