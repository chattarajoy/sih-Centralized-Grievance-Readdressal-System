// export class newForm {
//   constructor(
//     // public id: number,
//     // public name: string,
//     // public power: string,
//     // public alterEgo?: string
//
//     public subject : string,
//     public description : string,
//     public image : string,
//     public address : string,
//     public district : string,
//     public state : string,
//     public pincode : number
//   ) {  }
// }

export interface newForm{
    // name: string; // required, must be 5-8
    // contact : string;
    // email: string; // required, must be valid email format
    // password: string; // required, value must be equal to confirm password.
    // confirmPassword: string; // required, value must be equal to password.


    subject : string,
    description : string,
    image : string,
    address : string,
    district : string,
    state : string,
    pincode : number
}
