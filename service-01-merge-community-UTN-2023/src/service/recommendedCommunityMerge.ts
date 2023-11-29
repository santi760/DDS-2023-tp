import { Community } from "../types/community";
import { shouldMergeCommunities } from "./mergeCommunities";
import { PosibleMerge } from "../types/posibleMerge";


export function recommendedCommunityMerge(communities: Community[]): { community1: Community, community2: Community }[] {
  const possibleMerges: PosibleMerge[] = [];

  // Iterar sobre todas las combinaciones de comunidades
  for (let i = 0; i < communities.length; i++) {
    for (let j = i + 1; j < communities.length; j++) {
      const community1 = communities[i];
      const community2 = communities[j];

      if (shouldMergeCommunities(community1, community2)) {

        possibleMerges.push({ community1, community2 });
      }
    }
  }
  
  return possibleMerges;

}