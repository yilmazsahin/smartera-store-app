import {Product} from "./product";

export class Order {
  id!: number;
  billingAddress!: string;
  shippingAddress!: string;
  dateCreated!: Date;
  lastUpdated!: Date;
  totalPrice!: number;
  totalQuantity!: number;
  customerId!: string;
  products: Product[] = [];
}
