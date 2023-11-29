import { haveCommonMembers } from "../service/fusion";
import { Community } from "../types/community";
import { CommunityMember } from "../types/communityMember";

describe('haveCommonMembers', () => {
  // Prueba para verificar si dos comunidades tienen al menos un 5% de usuarios en común
  it('should return true if two communities have at least 5% common members', () => {

     const commonMembers: CommunityMember[] = [
      { id: "1", name: "Miembro 1" },
      { id: "2", name: "Miembro 2" },
      { id: "3", name: "Miembro 3" },
    ];

     const community1: Community = {
      id: "101",
      name: "Comunidad 1",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.9,
      members: [...commonMembers, { id: "4", name: "Miembro 4" }],
      interestingServices: [],
      interestingEstablishments: [],
    };

     const community2: Community = {
      id: "102",
      name: "Comunidad 2",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.8,
      members: [...commonMembers, { id: "5", name: "Miembro 5" }],
      interestingServices: [],
      interestingEstablishments: [],
    };

     const community3: Community = {
      id: "103",
      name: "Comunidad 3",
      lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
      degreeOfConfidence: 0.7,
      members: [ { id: "6", name: "Miembro 6" }],
      interestingServices: [],
      interestingEstablishments: [],
    };
  

    const minPercentage = 0.05;
    
    // Prueba con comunidades que cumplen el criterio
    expect(haveCommonMembers(community1, community2, minPercentage)).toBe(true);

    // Prueba con comunidades que no cumplen el criterio
    expect(haveCommonMembers(community1, community3, minPercentage)).toBe(false);
  });
});






