import swaggerJSDoc, { OAS3Definition, OAS3Options } from "swagger-jsdoc";

const swaggerDefinition: OAS3Definition = {
  openapi: "3.0.0",
  info: {
    title: "Documentacion de mi API",
    version: "1.0.0",
    description: "API para la fusión de comunidades. Grupo 01. Desarrolladores: Santiago Arrascaeta , Juan Ignacio Borda, Nicolas Galfione, Gonzalez Riverol Camila , Ignacio Mateo Villarruel.",
  },
  components: {
    schemas: {
        Community: {
            type: "object",
            required: ["id", "name", "degreeOfConfidence", "members", "interestingServices", "interestingEstablishments"],
            properties: {
                id: {
                type: "string",
              },
              name: {
                type: "string",
              },
              lastTimeMerged: {
                type: "Date",
              },
              degreeOfConfidence: {
                type: "number",
              },
              members: {
                type: "object",
                required: ["id", "name"],
                properties: {
                  id: {
                    type: "string",
                  },
                  name: {
                    type: "string",
                  },
                },
              },
              interestingServices: {
                type: "object",
                required: ["id", "name"],
                properties: {
                  id: {
                    type: "string",
                  },
                  name: {
                    type: "string",
                  },
                },
              },
              interestingEstablishments: {
                type: "object",
                required: ["id", "name"],
                properties: {
                  id: {
                    type: "string",
                  },
                  name: {
                    type: "string",
                  },
                },
            },
          },
        },
        PossibleMerge: {
            type: "object",
            required: ["community1", "community2"],
            properties: {
                community1: {
                type: "object",
                items: '#/components/schemas/Community'
              },
              community2: {
                type: "object",
                items: '#/components/schemas/Community'
              },
          },
        },
        MergeCommunities: {
          type: "object",
          required: ["community1", "community2","mergeCommunity"],
          properties: {
              community1: {
              type: "object",
              items: '#/components/schemas/Community'
            },
            community2: {
              type: "object",
              items: '#/components/schemas/Community'
            },
            mergeCommunity: {
              type: "object",
              items: '#/components/schemas/Community'
            },
        },
      },
    },
  },
};

const swaggerOptions: OAS3Options = {
  swaggerDefinition,
  apis: ["./src/routes/*.ts"],
};

export default swaggerJSDoc(swaggerOptions);
