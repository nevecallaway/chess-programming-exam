package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator implements ChessMovesCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        int[] rowMoves = {0, 1, 0, -1};
        int[] colMoves = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {
            int startRow = position.getRow();
            int startCol = position.getColumn();
            while (true) {
                startRow += rowMoves[i];
                startCol += colMoves[i];
                if (!onBoard(startRow, startCol)) {
                    break;
                }
                ChessPosition newPosition = new ChessPosition(startRow, startCol);
                ChessPiece newPiece = board.getPiece(newPosition);
                if (newPiece != null) {
                    if (newPiece.getTeamColor() != board.getPiece(position).getTeamColor()) {
                        validMoves.add(new ChessMove(position, newPosition, null));
                        break;
                    }
                    break;
                } else {
                    validMoves.add(new ChessMove(position, newPosition, null));
                }
            }
        }
        return validMoves;
    }
    private boolean onBoard(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }
}