import { InterestingEstablishment } from "../../types/interestingEstablishment";
import { Community } from "../../types/community";
import { CommunityMember } from "../../types/communityMember";
import { InterestingService } from "../../types/interestingServices";

// Definir algunos miembros, servicios interesantes y establecimientos interesantes
export const commonMembers: CommunityMember[] = [
  { id: "1", name: "Miembro 1" },
  { id: "2", name: "Miembro 2" },
  { id: "3", name: "Miembro 3" },
];

export const commonServices: InterestingService[] = [
  { id: "1", name: "Servicio 1" },
  { id: "2", name: "Servicio 2" },
  { id: "3", name: "Servicio 3" },
  { id: "4", name: "Servicio 4" },
];

export const commonEstablishments: InterestingEstablishment[] = [
  { id: "1", name: "Establecimiento 1" },
  { id: "2", name: "Establecimiento 2" },
  { id: "3", name: "Establecimiento 3" },
];

// Generar tres instancias de comunidad con miembros, servicios y establecimientos en común
export const community1: Community = {
  id: "101",
  name: "Comunidad 1",
  lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
  degreeOfConfidence: 0.9,
  members: [...commonMembers, { id: "4", name: "Miembro 4" }],
  interestingServices: [...commonServices, { id: "4", name: "Servicio 4" }],
  interestingEstablishments: [
    ...commonEstablishments,
    { id: "4", name: "Establecimiento 4" },
  ],
};

export const community2: Community = {
  id: "102",
  name: "Comunidad 2",
  lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
  degreeOfConfidence: 0.8,
  members: [...commonMembers, { id: "5", name: "Miembro 5" }],
  interestingServices: [...commonServices, { id: "5", name: "Servicio 5" }],
  interestingEstablishments: [
    ...commonEstablishments,
    { id: "5", name: "Establecimiento 5" },
  ],
};

export const community3: Community = {
  id: "103",
  name: "Comunidad 3",
  lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
  degreeOfConfidence: 0.7,
  members: [...commonMembers, { id: "6", name: "Miembro 6" }],
  interestingServices: [
    ...commonServices,
    
    { id: "6", name: "Servicio 6" },
    { id: "7", name: "Servicio 7" },
    { id: "8", name: "Servicio 8" },
    { id: "9", name: "Servicio 9" },
    { id: "10", name: "Servicio 10" },
  ],
  interestingEstablishments: [
    ...commonEstablishments,
    { id: "6", name: "Establecimiento 6" },
  ],
};
