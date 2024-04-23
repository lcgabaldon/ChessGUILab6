package figures;

public class Queen extends Rook {
  // Inherits constructor and properties from Rook
  public Queen() {
    super();
  }

  public Queen(PieceType type, Colorr color, Column column, int row) {
    super(type, color, column, row);
  }

  @Override
  public String toString() {
    return (this.type + " -- Color: " + this.color + ", Column: " + this.column + ", Row: " + this.row);
  }

  @Override
  public boolean moveTo(Column newColumn, int newRow) {
    // Check for Rook-like movement (horizontal or vertical)
    if (super.moveTo(newColumn, newRow)) {
      return true;
    }

    // Check for Bishop-like movement (diagonal)re
    int columnDiff = Math.abs(this.column.ordinal() - newColumn.ordinal());
    int rowDiff = Math.abs(this.row - newRow);
    if (columnDiff == rowDiff) {
      setPosition(newColumn, newRow); // Update position to the new column and row
      return true;
    }

    return false;
  }
}
