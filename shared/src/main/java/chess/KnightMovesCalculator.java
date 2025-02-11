package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMovesCalculator implements ChessMovesCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        int[] rowMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] colMoves = {-2, -1, 1, 2, 2, 1, -1, -2};

        for (int i = 0; i < 8; i++) {
            int row = position.getRow();
            int col = position.getColumn();
            row += rowMoves[i];
            col += colMoves[i];
            if (onBoard(row, col)) {
                ChessPosition newPosition = new ChessPosition(row, col);
                ChessPiece newPiece = board.getPiece(newPosition);
                if (newPiece == null || newPiece.getTeamColor() != board.getPiece(position).getTeamColor()) {
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
