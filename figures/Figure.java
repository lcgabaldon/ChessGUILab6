package figures;

import interfaces.IntFigure;

public abstract class Figure implements IntFigure {
  // provides the fields and constructors to initialize its values, but does not
  // implement method moveToprivate PieceType type;
  protected PieceType type;
  protected Colorr color;
  protected Column column;
  protected int row;

  // default constructor
  public Figure() {
  }

  public Figure(PieceType type, Colorr color, Column column, int row) {
    this.type = type;
    this.color = color;
    this.column = column;
    this.row = row;
  }

  // setters and getters
  public PieceType getType() {
    return this.type;
  }

  public Colorr getColor() {
    return this.color;
  }

  public Column getColumn() {
    return this.column;
  }

  public int getRow() {
    return row;
  }

  public void setPosition(Column column, int row) {
    this.column = column;
    this.row = row;
  }

  public abstract String toString();

  public abstract boolean moveTo(Column newColumn, int newRow);
}// end abstract Figure class