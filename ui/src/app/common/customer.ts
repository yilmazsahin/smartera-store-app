export class Customer {
  id!:string;
  firstName!: string;
  lastName!: string;
  email!: string;
  phoneNumber!: string;
  authorizationLevel!: string[];
  password!: string;
  orderAuthority?: boolean;
}
