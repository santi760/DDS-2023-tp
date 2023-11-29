import { haveSameDegreeOfConfidence } from "../service/fusion";
import { Community } from "../types/community";

const community1: Community = {
  id: "101",
  name: "Comunidad 1",
  lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
  degreeOfConfidence: 0.9,
  members: [],
  interestingServices: [],
  interestingEstablishments: [],
};

const community2: Community = {
  id: "102",
  name: "Comunidad 2",
  lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
  degreeOfConfidence: 0.9, // Mismo grado de confianza que community1
  members: [],
  interestingServices: [],
  interestingEstablishments: [],
};

const community3: Community = {
  id: "103",
  name: "Comunidad 3",
  lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
  degreeOfConfidence: 0.7, // Grado de confianza diferente a community1
  members: [],
  interestingServices: [],
  interestingEstablishments: [],
};

describe("haveSameDegreeOfConfidence", () => {
  // Prueba para verificar si dos comunidades tienen el mismo grado de confianza
  it("should return true if two communities have the same degree of confidence", () => {
    const result = haveSameDegreeOfConfidence(community1, community2);
    expect(result).toBe(true);
  });

  // Prueba para verificar si dos comunidades tienen diferentes grados de confianza
  it("should return false if two communities have different degrees of confidence", () => {
    const result = haveSameDegreeOfConfidence(community1, community3);
    expect(result).toBe(false);
  });
});
