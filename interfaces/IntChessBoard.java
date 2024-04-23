package interfaces;

import figures.Column;
import exceptions.InvalidCoordinateException;

public interface IntChessBoard {

  boolean verifyCoordinate(Column column, int row) throws InvalidCoordinateException;
}