import { commonEstablishments, commonMembers, commonServices } from "../mocks/community/community";
import { recommendedCommunityMerge } from "../service/recommendedCommunityMerge";
import { Community } from "../types/community";

describe("recommendedCommunityMerge", () => {
    it("should recommend possible mergers between two communities ", () => {
      const community1: Community = {
        id: "101",
        name: "Comunidad 1",
        lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
        degreeOfConfidence: 0.9,
        members: [...commonMembers, { id: "4", name: "Miembro 4" }],
        interestingServices: [
          ...commonServices,
          { id: "4", name: "Servicio 4" },
        ],
        interestingEstablishments: [
          ...commonEstablishments,
          { id: "4", name: "Establecimiento 4" },
        ],
      };

      const community2: Community = {
        id: "102",
        name: "Comunidad 2",
        lastTimeMerged: new Date('2022-02-15T12:00:00Z'),
        degreeOfConfidence: 0.7,
        members: [...commonMembers, { id: "5", name: "Miembro 5" }],
        interestingServices: [
          ...commonServices,
          { id: "5", name: "Servicio 5" },
        ],
        interestingEstablishments: [
          ...commonEstablishments,
          { id: "5", name: "Establecimiento 5" },
        ],
      };

      const community3: Community = {
        id: "103",
        name: "Comunidad 3",
        lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
        degreeOfConfidence: 0.8,
        members: [...commonMembers, { id: "5", name: "Miembro 5" }],
        interestingServices: [
          ...commonServices,
          { id: "5", name: "Servicio 5" },
        ],
        interestingEstablishments: [
          ...commonEstablishments,
          { id: "5", name: "Establecimiento 5" },
        ],
      };

      const community4: Community = {
        id: "104",
        name: "Comunidad 4",
        lastTimeMerged: new Date('2023-03-15T12:00:00Z'),
        degreeOfConfidence: 0.3,
        members: [...commonMembers, { id: "5", name: "Miembro 5" }],
        interestingServices: [
          ...commonServices,
          { id: "5", name: "Servicio 5" },
        ],
        interestingEstablishments: [
          ...commonEstablishments,
          { id: "5", name: "Establecimiento 5" },
        ],
      };

      const community5: Community = {
        id: "105",
        name: "Comunidad 5",
        lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
        degreeOfConfidence: 0.9,
        members: [...commonMembers, { id: "5", name: "Miembro 5" }],
        interestingServices: [
          ...commonServices,
          { id: "5", name: "Servicio 5" },
        ],
        interestingEstablishments: [
          ...commonEstablishments,
          { id: "5", name: "Establecimiento 5" },
        ],
      };

      const community6: Community = {
        id: "106",
        name: "Comunidad 6",
        lastTimeMerged: new Date('2022-03-15T12:00:00Z'),
        degreeOfConfidence: 0.8,
        members: [...commonMembers, { id: "5", name: "Miembro 5" }],
        interestingServices: [
          ...commonServices,
          { id: "5", name: "Servicio 5" },
        ],
        interestingEstablishments: [
          ...commonEstablishments,
          { id: "5", name: "Establecimiento 5" },
        ],
      };

      const communities: Community[] = [
        community1,
        community2,
        community3,
        community4,
        community5,
        community6,
      ];
      const recommendedCommunitiesMerge =
        recommendedCommunityMerge(communities);

      // Verificar que devuelva dos posibles fusiones
      expect(recommendedCommunitiesMerge).toHaveLength(2);
    });

  });

