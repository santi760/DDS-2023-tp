// todo:  en este archivo hacemos la funcion que se encarga de la fusion de las comunidades

import { Community } from "../types/community";

// El servicio sugerirá fusiones de comunidades. Para ello analizará las comunidades existentes y
// buscará las siguientes coincidencias (todos los parámetros numéricos definidos deben ser fácilmente
// modificables):



//* STEPS:

// ● Coincidan en más del 75% de los establecimientos observados
// ● Coincidan en más del 75% de los servicios estándares observados
// ● Tengan un mismo grado de confianza
// ● Tengan un 5% de usuarios en común

//! entonces lo que debemos hacer es tener una function que se llame fusion de comunidad y la logica esta distribuida en 4 funciones pequeñas que retornan un booleano si se cumple la condicion

//TODO IMPORTANTE. no tenemos que tener ningun valor harcodeado idealmente, estaria bueno recibirlo por parametro.
// podemos hacer que se reciba un numero que sea el porcentaje de cada cosa.

//** en cuanto a */

// ● No sé podrá tener 2 propuestas de fusión para una misma comunidad a la vez.
// ● No sé podrá repetir la propuesta de fusión de 2 comunidades por 6 meses.

//TODO: la funcion debe validar que no sea la misma comunidad a fusionar. es decir: com1 fusion com1 da error.
//TODO: la comunidad debe tener un atributo , date: ultimaPropuestaFusion. si ese atributo es menor a 6 meses, damos error.

// TODO: consultar en que se basa esto porque no lo entiendo. estimo que es devolver una comunidad nueva pero en cuanto a la inactivacion de las otras??

// El servicio, además, recibirá la aceptación o rechazo de una fusión. En caso de aceptación procederá a
// generar la fusión creando una nueva comunidad e inactivando las anteriores. La nueva comunidad será
// una combinación de las comunidades originales.

//*------------------------------ DEVELOPMENT ------------------------------*//

// Función para verificar si dos listas tienen un porcentaje mínimo de elementos en común (nos abstraemos)


export function haveCommonElements(
  list1: any[],
  list2: any[],
  minPercentage: number
) {
 
  const communElements: any[] = [];


  for (let elemLista1 = 0; elemLista1 < list1.length; elemLista1++) {
    for (let elemLista2 = 0; elemLista2 < list2.length; elemLista2++) {
      if (list1[elemLista1].id === list2[elemLista2].id) {
        if (
          !communElements.some((element) => element.id === list1[elemLista1].id)
        ) {
          communElements.push(list1[elemLista1]); 
        } 

      }
    }
  }

  const percentage = communElements.length / Math.max(list1.length, list2.length);

  return percentage >= minPercentage;
}





export function haveSameDegreeOfConfidence(
  community1: Community,
  community2: Community
) {
  return community1.degreeOfConfidence === community2.degreeOfConfidence;
}





// Función para verificar si dos comunidades tienen al menos un 5% de usuarios en común
export function haveCommonMembers(
  community1: Community,
  community2: Community,
  minPercentage: number
) {
  const commonMemberCount = community1.members.filter((member) =>
    community2.members.some((member2) => member2.id === member.id)
  ).length;
  const totalMembers = community1.members.length;
  return commonMemberCount / totalMembers >= minPercentage;
}

export function isDateMoreThan6MonthsAgo(dateString: any): boolean {
  const stringToDate = new Date(dateString);

  const sixMonthsInMilliseconds = 6 * 30 * 24 * 60 * 60 * 1000; // Approximately 30 days per month
  const currentDate = new Date();
  const sixMonthsAgo = new Date(currentDate.getTime() - sixMonthsInMilliseconds);

  return stringToDate <= sixMonthsAgo;
}


