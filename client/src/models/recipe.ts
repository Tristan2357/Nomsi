import type { Ingredient } from "./ingredient";
import type { Step } from "./step";

export class Recipe {
  constructor(
    public title: string,
    public ingredients: Array<Ingredient>,
    public steps: Array<Step>,
    public id: string
  ) {}
}
