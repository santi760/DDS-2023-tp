
import { CommunityMember } from "./communityMember";
import { InterestingEstablishment } from "./interestingEstablishment";
import { InterestingService } from "./interestingServices";

export type Community = {
  id: string;
  name: string;
  lastTimeMerged: Date;
  degreeOfConfidence : number;
  members: CommunityMember[];
  interestingServices: InterestingService[];
  interestingEstablishments : InterestingEstablishment[];
};
