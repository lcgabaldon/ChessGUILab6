// Edited by: Luis Gabaldon 10:42PM ==> Added verifyCoordinate implementation
package figures;

import interfaces.IntChessBoard;
import exceptions.InvalidCoordinateException;

public class ChessBoard implements IntChessBoard {
    @Override
    public boolean verifyCoordinate(Column column, int row) throws InvalidCoordinateException {
        if (column.ordinal() >= Column.A.ordinal() && column.ordinal() <= Column.H.ordinal() && row >= 1 && row <= 8) {
            return true;
        } else {
            throw new InvalidCoordinateException("Coordinate out of bounds: " + column + ", " + row);
        }
    }
}
