import {
  commonServices,
  community1,
  community2,
  community3,
} from "../mocks/community/community";
import { haveCommonElements } from "../service/fusion";
import { InterestingService } from "../types/interestingServices";

describe("haveCommonElements", () => {
  it("should work with arrays of objects and compare based on object properties", () => {
    const list1 = commonServices;
    const list2 = [
      { id: "1", name: "Servicio 1" },
      { id: "2", name: "Servicio 2" },
      { id: "3", name: "Servicio 3" },
      { id: "4", name: "Servicio 4" },
    ];

    const result = haveCommonElements(list1, list2, 0.75);
    expect(result).toBe(true);
  });

  it("should work with arrays of objects and compare based on object properties (false case)", () => {
    const list1 = commonServices;
    const list2: InterestingService[] = [
      { id: "7", name: "Servicio 7" },
      { id: "8", name: "Servicio 8" },
    ];

    const result = haveCommonElements(list1, list2, 0.75);
    expect(result).toBe(false);
  });

  it("should compare two communities' interestingServices arrays", () => {
    const community1Services = community1.interestingServices;
    const community2Services = community2.interestingServices;

    const result = haveCommonElements(
      community1Services,
      community2Services, 
      0.75
    );
    expect(result).toBe(true);
         
  });

  it("should compare two communities' interestingServices arrays (false case)", () => {
    const list1 = community1.interestingServices;
    const list2 = community3.interestingServices;

    const result = haveCommonElements(list1, list2, 0.75);
    
    expect(result).toBe(false);
  });

  // Puedes agregar más casos de prueba según sea necesario
});
