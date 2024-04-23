package figures;

public class King extends Queen {
  // default constructor
  public King() {
  }

  // constructor with parameters
  public King(PieceType type, Colorr color, Column column, int row) {
    super(type, color, column, row);
  }

  @Override
  public boolean moveTo(Column newColumn, int newRow) {
    int columnDiff = Math.abs(newColumn.ordinal() - this.column.ordinal());
    int rowDiff = Math.abs(newRow - this.row);
    return columnDiff <= 1 && rowDiff <= 1 && !(columnDiff == 0 && rowDiff == 0);
  }// end moveTo
}