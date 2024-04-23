package figures;

public class Bishop extends Figure {
  // empty constructor
  public Bishop() {
  }

  // constructor with parameters
  public Bishop(PieceType type, Colorr color, Column column, int row) {
    super(type, color, column, row);
  }

  @Override
  public String toString() {
    return (this.type + " -- Color: " + this.color + ", Column: " + this.column + ", Row: " + this.row);
  }// end toString

  @Override
  public boolean moveTo(Column newColumn, int newRow) {
    int columnDifference = Math.abs(newColumn.ordinal() - column.ordinal());
    int rowDifference = Math.abs(newRow - row);
    return columnDifference == rowDifference;
  }// end moveTo
}// end Rook