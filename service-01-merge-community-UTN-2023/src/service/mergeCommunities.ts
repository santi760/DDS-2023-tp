import { Community } from "../types/community";
import {
  haveCommonElements,
  haveCommonMembers,
  haveSameDegreeOfConfidence,
  isDateMoreThan6MonthsAgo
} from "./fusion";

export function shouldMergeCommunities(
  community1: Community,
  community2: Community
) {
  return (
    haveCommonElements(
      community1.interestingServices,
      community2.interestingServices,
      0.75
    ) &&
    haveCommonElements(
      community1.interestingEstablishments,
      community2.interestingEstablishments,
      0.75
    ) &&
    haveSameDegreeOfConfidence(community1, community2) &&
    haveCommonMembers(community1, community2, 0.75) &&
    isDateMoreThan6MonthsAgo(community1.lastTimeMerged) &&
    isDateMoreThan6MonthsAgo(community2.lastTimeMerged)
  );
}

//*------------------------------ MERGE COMMUNITY ------------------------------*//
const responseCommunities: Community[]= [];
export function mergeCommunities(
  community1: Community,
  community2: Community
): Community[] {
  if (shouldMergeCommunities(community1, community2)) {
    // Fusionar miembros, servicios interesantes y establecimientos interesantes
    const mergedMembers = mergeLists(
      community1.members,
      community2.members,
      compareObjectWithId
    );

    const mergedServices = mergeLists(
      community1.interestingServices,
      community2.interestingServices,
      compareObjectWithId
    );

    const mergedEstablishments = mergeLists(
      community1.interestingEstablishments,
      community2.interestingEstablishments,
      compareObjectWithId
    );

    // Crear la comunidad fusionada
    const mergedCommunity: Community = {
      id: "merged", // Asigna un ID adecuado o define la lógica para generar uno nuevo
      name: "Merged Community", // Define un nombre adecuado
      lastTimeMerged: new Date(), 
      degreeOfConfidence: community1.degreeOfConfidence, // Puedes elegir tomar el grado de confianza de una de las comunidades
      members: mergedMembers,
      interestingServices: mergedServices,
      interestingEstablishments: mergedEstablishments,
    };
    responseCommunities.push(community1);
    responseCommunities.push(community2);
    responseCommunities.push(mergedCommunity);

    return responseCommunities;
  }

  throw new Error("Las comunidades no pueden mergearse.");
}

//*------------------------------ HELPERS ------------------------------*//

// unir dos listas sin duplicados
function mergeLists(
  list1: any[],
  list2: any[],
  compareFn: (item1: any, item2: any) => boolean
): any[] {
  const communElements: any[] = [];
  const distinctElements1: any[] = []; // Elementos distintos de list1
  const distinctElements2: any[] = []; // Elementos distintos de list2

  for (let elemLista1 = 0; elemLista1 < list1.length; elemLista1++) {
    let found = false;
    for (let elemLista2 = 0; elemLista2 < list2.length; elemLista2++) {
      if (list1[elemLista1].id === list2[elemLista2].id) {
        if (
          !communElements.some((element) =>
            compareFn(element, list1[elemLista1])
          )
        ) {
          communElements.push(list1[elemLista1]);
        }
        found = true;
        break; // No es necesario seguir buscando en list2
      }
    }

    // Si el elemento de list1 no se encontró en list2, es distintivo de list1
    if (!found) {
      distinctElements1.push(list1[elemLista1]);
    }
  }

  // Ahora, buscamos elementos distintos de list2
  for (let elemLista2 = 0; elemLista2 < list2.length; elemLista2++) {
    let found = false;
    for (let elemLista1 = 0; elemLista1 < list1.length; elemLista1++) {
      if (list2[elemLista2].id === list1[elemLista1].id) {
        found = true;
        break; // No es necesario seguir buscando en list1
      }
    }

    // Si el elemento de list2 no se encontró en list1, es distintivo de list2
    if (!found) {
      distinctElements2.push(list2[elemLista2]);
    }
  }

  return [...communElements, ...distinctElements1, ...distinctElements2];
}





// Función de comparación para miembros
const compareObjectWithId = (object1: any, object2: any) =>
  object1.id === object2.id;
