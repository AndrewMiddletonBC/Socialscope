import React from 'react';
import styled from 'styled-components'
import { useNavigate } from 'react-router-dom';

import ContentContainer from '../components/ContentContainer';
import InputButton from '../components/Input/InputButton';

const IntroContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: stretch;
  width: 100%;
  min-width: 250px;

  @media screen and (max-width: 750px) {
    flex-direction: column;
    align-items: center;
  }
`;

const TitleSection = styled.div`
  display: flex;
  height: 100%;
  flex-direction: column;
  margin: 0 1rem 1.5rem 1rem;
  margin-bottom: 1.5rem;

  @media screen and (max-width: 750px) {
    min-width: 250px;
    align-items: center;
    text-align: center;
  }
`;

const PrimaryTitle = styled.div`
  font-size: 2.5rem;
  margin: 1rem 0;

  @media screen and (max-width: 450px) {
    padding: 0;
    margin: 0.5rem 0;
  }
`;

const SecondaryTitle = styled.div`
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
  margin-top: 2rem;

  @media screen and (max-width: 1650px) {
    margin-top: 0rem;
  }

  @media screen and (max-width: 450px) {
    margin-bottom: 1rem;
  }
`;

const StartButton = styled(InputButton)`

  margin-left: 1rem;
  margin-top: 14rem;
  margin-bottom: 1rem;

  @media screen and (max-width: 1650px) {
    margin-top: 10rem;
  }
   @media screen and (max-width: 1550px) {
    margin-top: 1rem;
  }
  @media screen and (max-width: 750px) {
    margin-left: 0;
  }
`

const ImageSection = styled.div`
  display: flex;
  flex: 1;
  justify-content: center;
  max-width: 500px;
  min-width: 250px;
  height: 100%;
  margin: 2rem;

`;

const DescriptionSection = styled.div`
  margin: 0.5rem 0;
  font-size: 1.5rem;
`;

const MiscSection = styled.div`
  margin: 1rem 0;
  font-size: 1rem;
`;

const LandingPage = () => {
  const navigate = useNavigate();

  const startRedirect = (e) => {
    e.preventDefault();
    navigate('/search');
  }

  return (
    <ContentContainer>
      <IntroContainer> 
        <TitleSection>
          <PrimaryTitle>
            Head Title
          </PrimaryTitle>
          <SecondaryTitle>
            Subheading tagline that is a bit longer
          </SecondaryTitle>
            <StartButton onClick={startRedirect}>
              Start Here
            </StartButton>
        </TitleSection>
        <ImageSection>
          <img src="https://source.unsplash.com/random/800x800/?img=1" />
        </ImageSection>
      </IntroContainer>
      <DescriptionSection>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      </DescriptionSection>
      <MiscSection>
        Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?
      </MiscSection>
    </ContentContainer>
  );
}

export default LandingPage;