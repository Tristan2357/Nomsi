export enum AmountUnit {
  GRAMM = "g",
  KILO_GRAMM = "kg",
  LITER = "l",
  MILLI_LITER = "ml",
  COUNT = "",
}

export function amountUnitAsArray(): AmountUnit[] {
  const helpAr = [];
  for (let amountUnitKey in AmountUnit) {
    // @ts-ignore
    helpAr.push(AmountUnit[amountUnitKey]);
  }
  return helpAr;
}

export class Ingredient {
  constructor(
    public name: string,
    public amount: number,
    public unit: AmountUnit
  ) {}
}
