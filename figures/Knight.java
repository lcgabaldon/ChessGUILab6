package figures;

public class Knight extends Figure {

  // empty constructor
  public Knight() {
  }

  // constructor with parameters
  public Knight(PieceType type, Colorr color, Column column, int row) {
    super(type, color, column, row);
  }

  @Override
  public String toString() {
    return (this.type + " -- Color: " + this.color + ", Column: " + this.column + ", Row: " + this.row);
  }

  @Override
  public boolean moveTo(Column newColumn, int newRow) {
    int columnDifference = Math.abs(newColumn.ordinal() - this.column.ordinal());
    int rowDifference = Math.abs(newRow - this.row);
    return (columnDifference == 2 && rowDifference == 1) || (columnDifference == 1 &&
        rowDifference == 2);
  }// end moveTo
}// end Knight