import {
  commonEstablishments,
  commonMembers,
  commonServices,
} from "../mocks/community/community";
import { shouldMergeCommunities } from "../service/mergeCommunities";
import { Community } from "../types/community";

describe("shouldMergeCommunities", () => {
  it("should return true if communities should be merged", () => {
    const community1: Community = {
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

    const community2: Community = {
      id: "102",
      name: "Comunidad 2",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.9,
      members: [...commonMembers, { id: "5", name: "Miembro 5" }],
      interestingServices: [...commonServices, { id: "5", name: "Servicio 5" }],
      interestingEstablishments: [
        ...commonEstablishments,
        { id: "5", name: "Establecimiento 5" },
      ],
    };

    const result = shouldMergeCommunities(community1, community2);
    expect(result).toBe(true);
  });


  

  it("no se pueden mergear porque tienen distinto grado de confianza", () => {
    // Configura las comunidades de manera que no cumplan con los criterios para la fusión
    // Ejemplo:
    const community1: Community = {
      id: "101",
      name: "Comunidad 1",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.9, //! Diferente grado de confianza
      members: [...commonMembers, { id: "4", name: "Miembro 4" }],
      interestingServices: [...commonServices, { id: "4", name: "Servicio 4" }],
      interestingEstablishments: [
        ...commonEstablishments,
        { id: "4", name: "Establecimiento 4" },
      ],
    };

    const community2: Community = {
      id: "102",
      name: "Comunidad 2",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.8, //! Diferente grado de confianza
      members: [...commonMembers, { id: "5", name: "Miembro 5" }],
      interestingServices: [...commonServices, { id: "5", name: "Servicio 5" }],
      interestingEstablishments: [
        ...commonEstablishments,
        { id: "5", name: "Establecimiento 5" },
      ],
    };

    const result = shouldMergeCommunities(community1, community2);
    expect(result).toBe(false);
  });


  //*------------------------------ TESTING ------------------------------*//

  it("should return false if communities have no common members", () => {
    const community1: Community = {
      id: "101",
      name: "Comunidad 1",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.9,
      members: [...commonMembers, { id: "4", name: "Miembro 4" }], //! Sin miembros en común
      interestingServices: [...commonServices, { id: "4", name: "Servicio 4" }],
      interestingEstablishments: [
        ...commonEstablishments,
        { id: "4", name: "Establecimiento 4" },
      ],
    };

    const community2: Community = {
      id: "102",
      name: "Comunidad 2",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.9,
      members: [], //! Sin miembros en común
      interestingServices: [...commonServices, { id: "5", name: "Servicio 5" }],
      interestingEstablishments: [
        ...commonEstablishments,
        { id: "5", name: "Establecimiento 5" },
      ],
    };

    const result = shouldMergeCommunities(community1, community2);
    expect(result).toBe(false);
  });


  //TODO Agregar más casos de prueba según sea necesario
});
