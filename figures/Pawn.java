package figures;

public class Pawn extends Figure {

  // empty constructor
  public Pawn() {
  }

  // constructor with parameters
  public Pawn(PieceType type, Colorr color, Column column, int row) {
    super(type, color, column, row);
  }

  @Override
  public String toString() {
    return (this.type + " -- Color: " + this.color + ", Column: " + this.column + ", Row: " + this.row);
  }

  @Override
  public boolean moveTo(Column newColumn, int newRow) {
    if (this.color == Colorr.WHITE) {
      return this.column == newColumn && newRow == this.row + 1;
    } else {
      return this.column == newColumn && newRow == this.row - 1;
    }
  }// end moveTo
}// end Pawn